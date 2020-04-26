package ru.mcx73.dao;


/*
Классы  DAO (Data Access Object) являются классами использующиеся для доступа в базу данных, например Query, Insert, Update, Delete.
Классы  DAO обычно аннотированыы с помощью  @Repository чтобы сказать  Spring управлять ими как  Spring BEAN.
Класс  AppUserDAO используется для манипуляции с таблицей APP_USER. Он имеет метод поиска пользователя в базе данных соответствующего
имени пользователя.
 */
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mcx73.entity.AppUser;

@Repository
@Transactional
public class AppUserDAO {

    @Autowired
    private EntityManager entityManager;

    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
