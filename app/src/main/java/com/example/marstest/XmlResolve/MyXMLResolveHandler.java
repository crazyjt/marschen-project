package com.example.marstest.XmlResolve;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by 钧童 on 2017/7/21.
 */
    /*
    * DefaultHandler是一个实现用于解析xml文件ContentHandler接口的类
    * DefaultHandler实现了接口的所有方法
    * 用MyXMLResolveHandler继承于DefaultHandler可以仅实现所需要的方法
    */
public class MyXMLResolveHandler extends DefaultHandler {
    String hisname,address,money,sex,status;
    String tagName;
    @Override
    public void startDocument() throws SAXException {
        System.out.println("--------startDocument--------");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("--------endDocument--------");
    }

    /*
    * 参数uri表示xml文件的命名控件
    * localName是没有前缀的标签名
    * qName是带前缀的便签名
    * attributes是标签的属性
    */
    //每个标签的开始都会调用startElement函数
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("qname: " + qName);
        System.out.println("localname: " + localName);
        tagName = localName;
        if(localName.equals("worker")){
            //获取标签的全部属性
            for(int i = 0; i < attributes.getLength(); i++){
                System.out.println(attributes.getLocalName(i) + "=" + attributes.getValue(i));
            }
        }
    }
    //每个标签结束都会调用endElement函数
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(localName.equals("worker")){
            printAll();
        }
    }
    /*
    * 得到标签的内容
    * ch是便签的内容数组
    * start是开始位置
    * length是读取的长度*/
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(tagName.equals("name"))
            hisname = new String(ch, start, length);
        if(tagName.equals("sex"))
            sex = new String(ch, start, length);
        if(tagName.equals("address"))
            address = new String(ch, start, length);
        if(tagName.equals("status"))
            status = new String(ch, start, length);
        if(tagName.equals("money"))
            money = new String(ch, start, length);
    }

    //自定义一个方法，输出标签的所有内容
    public void printAll(){
        System.out.println("name:   " + hisname);
        System.out.println("sex:    " + sex);
        System.out.println("status:    " + status);
        System.out.println("address:    " + address);
        System.out.println("money:    " + money);
        System.out.println();
    }
}
