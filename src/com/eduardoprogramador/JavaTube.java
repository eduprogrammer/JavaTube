package com.eduardoprogramador;

import com.eduardoprogramador.http.Http;
import com.eduardoprogramador.http.DownloadHandler;
import com.eduardoprogramador.utils.Tokenize;

/*
 *   A Java class library for download videos (mp4) and audios (mp3) from Youtube.
 *   Copyright 2022. Eduardo Programador
 *   consultoria@eduardoprogramador.com
 *   www.eduardoprogramador.com
 *
 *   All Rights Reserved
 * */

import org.json.JSONObject;
import java.util.ArrayList;

public class JavaTube {
    /*
    *   An interface that will handler the download link.
    *   Here, you must choose whatever action you like
    * */
    private static DownloadHandler handler;
    /*
    * Choose the FORMAT_VIDEO for mp4 download
    * */
    public static final int FORMAT_VIDEO = 0x5;

    /*
    *  Choose the FORMAT_AUDIO for mp3 download
    * */
    public static final int FORMAT_AUDIO = 0xff;

    /*
    *   The class has a private constructor.
    *   You will not get instatiate the class with the default constructor.
    *   Use the static method, instead.
    * */
    private JavaTube(){}

    /*
    *   The static method for initiate the class.
    * */
    public static JavaTube init() {
        return new JavaTube();
    }

    /*
    *   The main method for start the download task.
    *   After start the class object from the static method,
    *   and after implements the interface,
    *   call this method with the following parameters:
    *
    *   linkVideo: The video link from Youtube.
    *   formatDownload: One of the following formats:
    *               FORMAT_AUDIO: for mp3 files
    *               FORMAT_VIDEO: for mp4 files
    * */
    public void startDownload(String linkVideo, int formatDownload) throws TubeException {

        String format = null;

        switch (formatDownload) {
            case FORMAT_AUDIO:
                format = "mp3";
                break;

            case FORMAT_VIDEO:
                format = "mp4";
                break;

            default:
                throw new TubeException("Invalid Download Format");
        }

        Http http = Http.buildRequest();
        String res = null;

        ArrayList<ArrayList> fp = new ArrayList<>();

        ArrayList<String> fp01 = new ArrayList<>();
        fp01.add("pass");
        fp01.add(Tokenize.init("aml6ZndpdHV3dGx3ZnJmaXR3M2h0cg==") + "_copyright_2022");
        fp.add(fp01);
        ArrayList<String> fp02 = new ArrayList<>();
        fp02.add("link");
        fp02.add(linkVideo);
        fp.add(fp02);
        ArrayList<String> fp03 = new ArrayList<>();
        fp03.add("format");
        fp03.add(format);
        fp.add(fp03);

        ArrayList<ArrayList> fpH = new ArrayList<>();

        ArrayList<String> fpH01 = new ArrayList<>();


        if((res = http.post(Tokenize.init("aml6ZndpdHV3dGx3ZnJmaXR3M2h0cg=="),true,443,Tokenize.init("NHVtdTR+dHppemdqM3VtdQ=="),fp,null)) != null) {

            try {
                JSONObject jsonBase = new JSONObject(res);

                String key = jsonBase.getString("key");
                String unix = jsonBase.getString("unix");
                String id = jsonBase.getString("id");
                String options = jsonBase.getString("options");
                String title = jsonBase.getString("title");

                String res2 = null;

                ArrayList<ArrayList> sp = new ArrayList<>();

                ArrayList<String> sp01 = new ArrayList<>();
                sp01.add("v_id");
                sp01.add(id);
                sp.add(sp01);
                ArrayList<String> sp02 = new ArrayList<>();
                sp02.add("ftype");
                sp02.add(format);
                sp.add(sp02);
                ArrayList<String> sp03 = new ArrayList<>();
                sp03.add("fquality");
                sp03.add(options);
                sp.add(sp03);
                ArrayList<String> sp04 = new ArrayList<>();
                sp04.add(Tokenize.init("eXRwanM="));
                sp04.add(key);
                sp.add(sp04);
                ArrayList<String> sp05 = new ArrayList<>();
                sp05.add("timeExpire");
                sp05.add(unix);
                sp.add(sp05);
                ArrayList<String> sp06 = new ArrayList<>();
                sp06.add("client");
                if(format.equalsIgnoreCase("mp4"))
                    sp06.add(Tokenize.init("aWt4a3hpazN4aXBr"));
                else
                    sp06.add(Tokenize.init("fjdyanlmM2h0cg=="));
                sp.add(sp06);

                ArrayList<ArrayList> keyH = new ArrayList<>();

                ArrayList<String> keyH01 = new ArrayList<>();
                keyH01.add(Tokenize.init("fTJ3anZ6anh5amkycGp+"));
                keyH01.add(Tokenize.init("aWo1aGt6bnd5bGs7PGY="));
                keyH.add(keyH01);

                if((res2 = http.post(Tokenize.init("Z2ZocGpzaTN4e2hqc3lqdzN9fn8="),true,443,Tokenize.init("NGZ1bjRodHN7and5Mmd+Mjk6a2g5Z2o9PjY7PjY7Z2Y4Zz1pOzZpaTtqNWk7Pj45"),sp,keyH)) != null) {

                    JSONObject jsonSecond = new JSONObject(res2);
                    String stSecond = jsonSecond.getString("c_status");
                    if(stSecond.equalsIgnoreCase("ok")) {
                        String server = (format.equalsIgnoreCase("mp4")) ? jsonSecond.getString("c_server") : jsonSecond.getString("d_url");

                        if(format.equalsIgnoreCase("mp3")) {
                            if(handler != null) {
                                handler.onDownload(server);
                                return;
                            } else {
                                throw new TubeException("Interface Needed. Implement it before calling the startDownload Method");
                            }
                        }

                        server += "/api/json/convert";
                        String res3 = null;

                        String[] inPieces = server.split("//");
                        String inPiecesTwo = inPieces[1];
                        String[] inPiecesThree = inPiecesTwo.split("/");
                        StringBuilder builderPieces = new StringBuilder();
                        for (int i = 1; i < inPiecesThree.length; i++) {
                            builderPieces.append("/" + inPiecesThree[i]);
                        }

                        ArrayList<ArrayList> tp = new ArrayList<>();

                        tp.add(sp01);
                        tp.add(sp02);
                        tp.add(sp03);
                        ArrayList<String> name = new ArrayList<>();
                        name.add("fname");
                        name.add(title);
                        tp.add(name);
                        tp.add(sp04);
                        tp.add(sp05);


                        if((res3 = http.post(inPiecesThree[0],true,443,builderPieces.toString(),tp,null)) != null) {

                            JSONObject jsonThree = new JSONObject(res3);

                            String stThree = jsonThree.getString("status");
                            if(stThree.equalsIgnoreCase("success")) {
                                String linkDownload = jsonThree.getString("result");

                                if(handler != null) {
                                    handler.onDownload(linkDownload);
                                } else {
                                    throw new TubeException("Interface Needed. Implement it before calling the startDownload Method");
                                }

                            } else {
                                throw new TubeException("Third Call Failed");
                            }

                        } else {
                            throw new TubeException("Third Call Failed");
                        }

                    } else {
                        throw new TubeException("Second Call Failed");
                    }

                } else {
                    throw new TubeException("Second Call Failed");
                }


            } catch (Exception ex) {
                throw new TubeException("Link Provided Error");
            }

        } else {
            throw new TubeException("First Call Failed");
        }
    }

    /*
    * Interface for making some job with the link returned from the startDownload method.
    *
    * Parameters:
    *
    * downloadListener: You must provide the implementation for the interface.
    *           The interface method has a string that contains the link for the download task.
    *           From now, you can either make a GET request on your browser for start the download
    *           or implements a http client on the background.
    * */
    public static void setOnDownloadListener(DownloadHandler downloadListener) {
        handler = downloadListener;
    }


}

