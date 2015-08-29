package com.beaconsolutions.maestroid.FileHandler;

import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.TaskManager.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

/**
 * Created by Chamil Prabodha on 27/07/2015.
 */
public class XMLParser {

    private static XMLParser instance = null;

    public static XMLParser getInstance(){

        if(instance==null)
            instance = new XMLParser();

        return instance;
    }

    public ArrayList<Level> parseXML(Context context) throws XmlPullParserException, IOException {

        Task task = null;
        Level level = null;

        ArrayList<Level> List = new ArrayList<Level>();

        XmlPullParser parser = context.getResources().getXml(R.xml.tasklist);

        int eventType = parser.getEventType();
        String Task_Type = null;
        String name = null;
        while(eventType!=XmlPullParser.END_DOCUMENT){

            switch (eventType){

                case XmlPullParser.START_DOCUMENT:


                    break;

                case XmlPullParser.START_TAG:

                    name = parser.getName();

                    if(name.equalsIgnoreCase("Level")){
                        level = new Level();
                        level.init();
                        if(level!=null){
                            level.setLevelType(parser.getAttributeValue(null,"Type"));
                            level.setLevelDescription(parser.getAttributeValue(null,"Description"));
                        }
                    }

                    if(name.equalsIgnoreCase("Task")){

                        Task_Type = parser.getAttributeValue(null,"type");

                        if(Task_Type.equalsIgnoreCase("Scale")){

                            task = new Task_Scale();
                            ((Task_Scale)task).init();

                        }

                    }

                    else if(name.equalsIgnoreCase("Attributes")){
                        task.setCorrect_answer(parser.getAttributeValue(null,"correct"));
                    }

                    else if(name.equalsIgnoreCase("Answers")){

                        task.getAnswers().add(parser.getAttributeValue(null,"incorrect"));
                    }

                    else if(name.equalsIgnoreCase("Note")){

                        Note note = new Note();
                        note.setNote_value(Integer.parseInt(parser.getAttributeValue(null,"id")));

                        if(task!=null){
                            if(Task_Type.equals("Scale")){

                                ((Task_Scale)task).getNotes().add(note);

                            }
                        }
                    }

                    break;

                case XmlPullParser.END_TAG:
                    name = parser.getName();

                    if(name.equalsIgnoreCase("Task")){
                        if(level!=null && task!=null){
                            level.getTasks().add(task);
                        }
                    }

                    if(name.equalsIgnoreCase("Level")){
                        if(level!=null){
                            List.add(level);
                        }
                    }

                    break;
            }
            eventType = parser.next();
        }


        return List;
    }
}
