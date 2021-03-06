package com.laravelshao.learning.pattern.creational.abstractfactory;

import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Connection;

/**
 * 抽象工厂模式测试
 *
 * <p>在工厂方法模式中具体工厂负责生产具体的产品，每一个具体工厂对应一种具体产品，工厂方法也具有唯一性，
 * 一般情况下，一个具体工厂中只有一个工厂方法或者一组重载的工厂方法。但是有时候我们需要一个工厂可以提供
 * 多个产品对象，而不是单一的产品对象。
 *
 * <p>产品等级结构：产品等级结构即产品的继承结构，如一个抽象类是电视机，其子类有海尔电视机、海信电视机、
 * TCL电视机，则抽象电视机与具体品牌的电视机之间构成了一个产品等级结构，抽象电视机是父类，而具体品牌的电视机是其子类。
 *
 * <p>产品族：在抽象工厂模式中，产品族是指由同一个工厂生产的，位于不同产品等级结构中的一组产品，如海尔电器工厂
 * 生产的海尔电视机、海尔电冰箱，海尔电视机位于电视机产品等级结构中，海尔电冰箱位于电冰箱产品等级结构中。
 *
 * <p>当系统所提供的工厂所需生产的具体产品并不是一个简单的对象，而是多个位于不同产品等级结构中属于不同类
 * 型的具体产品时需要使用抽象工厂模式。抽象工厂模式是所有形式的工厂模式中最为抽象和最具一般性的一种形态。
 *
 * <p>抽象工厂模式与工厂方法模式最大的区别在于，工厂方法模式针对的是一个产品等级结构，而抽象工厂模式则需要
 * 面对多个产品等级结构，一个工厂等级结构可以负责多个不同产品等级结构中的产品对象的创建 。当一个工厂等级结构
 * 可以创建出分属于不同产品等级结构的一个产品族中的所有对象时，抽象工厂模式比工厂方法模式更为简单、有效率。
 *
 * <p>抽象工厂模式案例：{@link SqlSessionFactory} 和 {@link Connection}
 *
 * @author shaoqinghua
 * @date 2017/6/1
 * @description
 * @see <a ref="https://design-patterns.readthedocs.io/zh_CN/latest/creational_patterns/abstract_factory.html">抽象工厂模式图解</a>
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Note note = courseFactory.getNote();
        video.play();
        note.write();
    }
}
