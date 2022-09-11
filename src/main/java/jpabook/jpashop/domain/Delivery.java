package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    //ordinal 은 숫자로 들어간다. 중간에 다른 상태가 생기면 망함. 웬만하면 String 으로 설정
    @Enumerated(EnumType.STRING)
    private DeliverStatus status;


}