package com.example.marstest.download;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.marstest.R;

/**
 * Created by 钧童 on 2017/7/17.
 */

public class Download extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        Button txt = (Button)findViewById(R.id.btnDownloadTxt);
        Button mp3 = (Button)findViewById(R.id.btnDownloadMP3);

        txt.setOnClickListener(new txtListener());
        mp3.setOnClickListener(new mp3Listener());
    }

    class txtListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TxtThread runableThread = new TxtThread();
            new Thread(runableThread, "txtThread").start();
        }
    }

    class mp3Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            MP3Thread runableThread = new MP3Thread();
            new Thread(runableThread, "MP3Thread").start();
        }
    }

    class TxtThread implements Runnable{
        @Override
        public void run() {
            HttpDownloader httpDownloader = new HttpDownloader();
            String txtStr = httpDownloader.downloadTxt("http://dzs.qisuu.com/txt/%E6%8B%A9%E5%A4%A9%E8%AE%B0.txt");
            System.out.println(txtStr);
        }
    }
    class MP3Thread implements Runnable{
        @Override
        public void run() {
            HttpDownloader httpDownloader = new HttpDownloader();
            int i = httpDownloader.downloadMP3("http://d11.baidupcs.com/file/ac6c43309dacd1a6758ed89cb7fc962d?bkt=p3-00004cebfbae8f6e246653ca106acd7867c6&xcode=acab5707ee8d9c5e8b7b0ba4e241906ded963bf7ccc709790b2977702d3e6764&fid=1633013378-250528-717319788659797&time=1500425550&sign=FDTAXGERLBHS-DCb740ccc5511e5e8fedcff06b081203-FQ3fvqOSGi0OveqpFV8ZvFbbbj4%3D&to=d11&size=12487575&sta_dx=12487575&sta_cs=1200&sta_ft=mp3&sta_ct=0&sta_mt=0&fm2=MH,Yangquan,Netizen-anywhere,,guangdong,ct&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=00004cebfbae8f6e246653ca106acd7867c6&sl=83034191&expires=8h&rt=pr&r=430704936&mlogid=4623948414867186702&vuk=1633013378&vbdid=458917591&fin=%E8%96%9B%E4%B9%8B%E8%B0%A6+-+%E6%9A%A7%E6%98%A7.mp3&fn=%E8%96%9B%E4%B9%8B%E8%B0%A6+-+%E6%9A%A7%E6%98%A7.mp3&rtype=1&iv=0&dp-logid=4623948414867186702&dp-callid=0.1.1&hps=1&csl=300&csign=j%2BdBOH%2F3lTcOLsKNWtExcQxM%2FGo%3D&so=0&ut=6&uter=4&serv=0&by=themis", "path", "fileName");
            System.out.println(i);
        }
    }
}
