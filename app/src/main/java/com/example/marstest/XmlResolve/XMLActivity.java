package com.example.marstest.XmlResolve;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.marstest.R;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by 钧童 on 2017/7/21.
 */

public class XMLActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_resolve);

        Button resolve = (Button)findViewById(R.id.btnXmlResolve);
        resolve.setOnClickListener(new resolveListener());
    }

    class resolveListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            File file = new File("D:/Android/Project/MarsTest/app/workers.xml");
            StringBuffer stringBuffer = null;
            String line = null;
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    stringBuffer.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                //创建一个SAXParseFatory解析器工厂
                SAXParserFactory factory = SAXParserFactory.newInstance();
                //XMLReader用于逐行扫描xml的内容，扫描到一些标签会触发事件
                XMLReader reader = factory.newSAXParser().getXMLReader();
                //为XMLReader设置内容处理器
                reader.setContentHandler(new MyXMLResolveHandler());
                //开始解析文件
                //StringReader是以流的方式读取字符串
                reader.parse(new InputSource(new StringReader(stringBuffer.toString())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
