package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

//    public List<Order> findAll(OrderSearch orderSearch){
//        String jpql = "select o from Order o join o.member m";
//        boolean isFirstCondition = true;
//
//        //주문 상태 검색
//        if (orderSearch.getOrderStatus() != null) {
//            if(isFirstCondition) {
//                jpql += " where";
//                isFirstCondition = false;
//            } else {
//                jpql += " and";
//            }
//            jpql += " o.status = :status";
//        }
//        return em.createQuery(jpql, Order.class
//                .setMaxResults(1000)
//                .getResultList();
//    }
}
