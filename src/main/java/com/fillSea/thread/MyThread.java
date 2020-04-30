package com.fillSea.thread;


public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(this.getName());//打印线程的名称，也可以setName设置线程的名称
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        System.out.println(Thread.currentThread().getName());//打印线程的名称

        try {
            thread.sleep(1000);//线程的sleep方法
            System.out.println("sleep ,wait");

            new Thread(new MyRunable()).start();//runable方式

            new Thread(){
                @Override
                public void run() {
                    System.out.println("我是创建匿名内部类的第一种方式");
                }
            }.start();

            new Thread(new Runnable(){

                public void run() {
                    System.out.println("我是创建匿名内部类的第二种方式");
                }
            }).start();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
class MyRunable implements Runnable{

    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}