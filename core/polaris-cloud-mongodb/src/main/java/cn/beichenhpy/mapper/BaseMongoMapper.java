package cn.beichenhpy.mapper;

import cn.beichenhpy.annotation.QueryField;
import cn.beichenhpy.modal.Page;
import cn.beichenhpy.utils.SpringContextUtil;
import cn.beichenhpy.utils.reflect.ReflectionUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 基于matecloud-starter-mongodb改造
 * @see <a href="https://github.com/matevip/matecloud/tree/dev/mate-core/mate-starter-mongodb">mate-starter-mongodb</a>
 * @since 2021/7/23 20:52
 */
public interface BaseMongoMapper<T> {


    /**
     * 保存一个对象到mongodb
     *
     * @param entity 某实体类
     * @return 返回
     */
    default T save(T entity){
        return mongoTemplate().save(entity);
    }

    /**
     * 根据id删除对象
     *
     * @param t 对象
     */
    default void deleteById(T t){
       mongoTemplate().remove(t);
    }

    /**
     * 根据对象的属性删除
     *
     * @param t 传输的实体类
     */
    default void deleteByCondition(T t){
        Query query = buildBaseQuery(t);
        mongoTemplate().remove(query, getEntityClass());
    }


    /**
     * 根据id进行更新
     *
     * @param id ObjectId
     * @param t 新的对象
     */
    default void updateById(String id, T t){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = buildBaseUpdate(t);
        update(query, update);
    }


    /**
     * 根据对象的属性查询
     *
     * @param t 查询的实体类
     * @return 该实体类的集合
     */
    default List<T> findByCondition(T t){
        Query query = buildBaseQuery(t);
        return mongoTemplate().find(query, getEntityClass());
    }


    /**
     * 通过条件查询实体(集合)
     *
     * @param query 查询条件
     */
    default List<T> find(Query query){
       return mongoTemplate().find(query, this.getEntityClass());
    }

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query 查询条件
     * @return 返回实体类
     */
    default T findOne(Query query){
        return mongoTemplate().findOne(query, this.getEntityClass());
    }

    /**
     * 通过条件查询更新数据
     *
     * @param query 条件
     * @param update 更新条件
     */
    default void update(Query query, Update update){
        mongoTemplate().updateMulti(query, update, this.getEntityClass());
    }


    /**
     * 通过ID获取记录
     *
     * @param id ObjectId
     * @return 某实体类
     */
    default T findById(String id){
        return mongoTemplate().findById(id, this.getEntityClass());
    }

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     *
     * @param id ObjectId
     * @param collectionName 集合名
     * @return 某实体类
     */
    default T findById(String id, String collectionName){
        return mongoTemplate().findById(id, this.getEntityClass(), collectionName);
    }

    /**
     * 通过条件查询,查询分页结果
     *
     * @param page 分页信息
     * @param query 查询条件
     * @return 分页结果
     */
    default Page<T> findPage(Page<T> page, Query query){
        //如果没有条件 则所有全部
        query = query == null ? new Query(Criteria.where("_id").exists(true)) : query;
        long count = this.count(query);
        // 总数
        page.setTotalCount((int) count);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPageSize();
        query.skip((long) (currentPage - 1) * pageSize).limit(pageSize);
        List<T> rows = this.find(query);
        page.build(rows);
        return page;
    }

    /**
     * 求数据总和
     *
     * @param query 查询条件
     * @return 数据总和
     */
    default long count(Query query){
        return mongoTemplate().count(query, this.getEntityClass());
    }


    /**
     * 获取MongoDB模板操作
     *
     * @return mongoTemplate
     */
    default MongoTemplate mongoTemplate(){
        return SpringContextUtil.getBean(MongoTemplate.class);
    }
    /**
     * 根据vo构建查询条件Query
     *
     * @param t VO
     * @return 返回查询条件
     */
    private Query buildBaseQuery(T t) {
        Query query = new Query();

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (value != null) {
                    QueryField queryField = field.getAnnotation(QueryField.class);
                    if (queryField != null) {
                        query.addCriteria(queryField.type().buildCriteria(queryField, field, value));
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return query;
    }

    /**
     * 获取需要操作的实体类class
     *
     * @return class
     */
    private Class<T> getEntityClass() {
        return ReflectionUtil.getSuperClassGenricType(getClass());
    }


    /**
     * 根据vo构建更新条件Query
     *
     * @param t 实体类
     * @return 更新条件
     */
    private Update buildBaseUpdate(T t) {
        Update update = new Update();

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(t);
                if (value != null) {
                    update.set(field.getName(), value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return update;
    }
}
