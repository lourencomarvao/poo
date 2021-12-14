package special;


import java.util.List;

import org.joda.time.Interval;

import special.Algorithms.IdealAlg;
import special.Algorithms.MiddleAlg;
import special.Algorithms.SimpleAlg;
import special.Loaders.LectureLoader;
import special.Loaders.RoomLoader;
import special.Models.Lecture;
import special.Models.Room;

class Worker{
    private static final String filename1 = "C:\\Users\\loure\\Cliente_Especial\\iscte-1\\src\\Local_Files\\ADS - Caracterizacao das salas.csv";
    private static final String filename2 = "C:\\Users\\loure\\Cliente_Especial\\iscte-1\\src\\Local_Files\\ADS - Exemplo de horario do 1o Semestre.csv";

    public Worker(){
        List<Room> rooms = RoomLoader.readRoomFile(filename1);
        List<Lecture> lectures = LectureLoader.readLecturePath(filename2);
        //File reading if fully operational   
        //System.out.println(rooms);
        //System.out.println(lectures);
    
/*
        // Basic Alghoritm that acts as a FIFO
        SimpleAlg sa = new SimpleAlg();
        sa.compute(lectures, rooms);
        for(Lecture l : lectures){
            System.out.println(l.getRoom().getName());
        }

        // Middle Algorithm that acts based on capacity and required characteristic
        MiddleAlg ma = new MiddleAlg();
        ma.compute(lectures, rooms);
        for(Lecture l : lectures){
            if(l.getRoom() != null)
                System.out.println(l.getRoom().getName());
        }
*/
        // Ideal Alghoritm that allocates room according to their capacity/characteristic/availabity
        IdealAlg ia = new IdealAlg();
        ia.compute(lectures, rooms);
        
/*
        for(Lecture l : lectures){
            if(l.getRoom() != null)
                System.out.println(l.getRoom().getName());
        }
*/

        //check if there are no conflitcs in the schedules
        for(Room r : rooms){
            System.out.println("Room: " + r.getName());
            for(Interval i : r.getLectures_times_booked()){
                System.out.println("Lecture: " + i.getStart() +" Ends at: "+ i.getEnd());
            }
        }
        
        // Should have the rooms allocated to each lecture instance
        
        
    }
    

    public static void main(String[] args) {
       new Worker();
    }
}