package com.scxh.android.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 **观察者模式：定义了对象之间的一对多依赖关系，当一个对象（主题对象）的状态改变时，它的所有依赖者（观察者对象）都会收到通知并自动更新。
 *
 * 观察者模式实现了主题对象与观察者对象之间的松耦合
 * ，当有新的观察者时，无需修改主题对象的代码，只需要新的观察者对象实现接口。在程序运行的过程中，可以随时注册和删除观察者而不影响主体对象。
 * 
 * 观察者模式使主题（Subject）对象和观察者（Observer）对象都可以被轻易地复用。
 * 
 * 软件设计原则：努力在交互对象之间实现松耦合，使它们之间的互相依赖降到最低，从而提高可复用性。
 * 
 * Java内置了对观察者模式的支持：java.util.Observable类和java.util.Observer接口。
 * java.util.Observable类的局限：
 * 1、它是一个类，而不是接口，由于Java不支持多重继承，所以主题类无法同时拥有它和另一个超类的行为，这限制了Observable类的复用潜力
 * 。违反了“针对接口编程，而不是针对实现编程”的软件设计原则。
 * 
 * 2、它的某些如setChanged()这样的方法被定义为protected，要使用它们就必须继承Observable类
 * ，这违反了“多用组合，少用继承”的软件设计原则。 如果上面两条限制妨碍了你的使用，就应该考虑自己设计实现观察者模式。
 * 在观察者模式中，传递数据的方式有“推”和“拉”两种，Java内置的实现支持这两种方式，然而较常用的为“推”数据。
 * 观察者模式以松耦合的方式在对象之间传递状态，MVC是其代表。
 * 
 * @author viktor
 * 
 */
public class Blogger implements Subject {
    private List<Observer> observers;

    private String blog;

    public Blogger() {
        observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(blog);
        }
    }

    // 发表新博客文章
    public void writeNewBlog(String blog) {
        this.blog = blog;
        notifyObservers();
    }

    // 邮件订阅者
    public class EmailSubscriber implements Observer {
        public void update(String blog) {
            // 发送电子邮件
        }
    }

    // RSS订阅者
    public class RssSubscriber implements Observer {
        public void update(String email) {
            // 更新RSS信息
        }
    }
}
