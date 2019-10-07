package com.mysqldemo.demo.videoservice;

public interface VlcjPlayerService {


    void smainV() throws InterruptedException;

    void playC();

    void pausedC();

    void stopC();

    void fullC();

    long positionC();

    void leftC();

    void rightC();

    void timeC(int time);

    void nextC();

    void startVideoFrame();

    void smainVD();

    void StartK(String command);

    void mini();

    void vup();

    void vdown();

    void smainVDWithNotRabit();
}
