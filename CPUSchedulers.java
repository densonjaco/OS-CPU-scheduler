
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dennissonjaco
 */
public class CPUSchedulers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Integer> tr = new ArrayList<>();
        
        int bt[] = new int[20]; 
        int totalbt[] = new int[20];
        int totalWt[]=new int[20];  
        int completefinaltime[] = new int[20];       
        int wt[] = new int[20];
        int clock = 0;
        int ft[] = new int[20];
        int at[] = new int[20];
        int ioBurst[] = new int[20];
        int totalioBurst[] = new int[20];
        double wtAvg[] = new double[8];
        int wtTotal[] = new int[20];
        int totalwt = 0;
        int ftTotal = 0;
        int m=0;
        int size = 0;
        int lastburst = 0;
        Queue<Integer> readyque = new LinkedList<>();
        int ttr[] = new int[20];
        int totalttr[] = new int[20];
        int ttrtotal = 0;
        int timeReady[]=new int[20];
        int avgtrtotal = 0;
        ArrayList<Integer> unsorted = new ArrayList<Integer>();
        int completefinaltimetotal = 0;
        ArrayList<Integer>[] process = new ArrayList[130];   
        
        
            process[1] = new ArrayList<>();Collections. addAll(process[1], 5,27,3,31,5,43,4,18,6,22,4,26,3,24,5);
            process[2] = new ArrayList<>();Collections. addAll(process[2],4,48, 5,44, 7,42, 12,37, 9,76, 4,41, 9,31, 7,43, 8);
            process[3] = new ArrayList<>();Collections. addAll(process[3],8,33, 12,41, 18,65, 14,21, 4,61, 15,18,14,26,5,31,6);
            process[4] = new ArrayList<>();Collections. addAll(process[4],3, 35,4,41, 5,45,  3,51, 4,61,  5,54,  6,82, 5,77,  3);
            process[5] = new ArrayList<>();Collections. addAll(process[5],16, 24,17,21,5,36,16,26,7,31,13,28,11, 21,6, 13,3, 11,4);
            process[6] = new ArrayList<>();Collections. addAll(process[6],11,22,4,8, 5,10,6,12,7,14,9,18,12,24,15,30,8);
            process[7] = new ArrayList<>();Collections. addAll(process[7],14,46,17,41,  11,42,  15,21,  4,32,  7,19, 16,33, 10);
            process[8] = new ArrayList<>();Collections. addAll(process[8],4, 14,5,33,  6,51,  14,73,  16,87,  6);
            Queue<Integer> quelist = new LinkedList<>();
       wtTotal[0] = 0; 
       totalttr[0]=0;
       timeReady[1]=0;
for (int i=1;i<=8;i++){
    quelist.add(i);
    }
Queue<Integer> toBeRemoved = new LinkedList<>();
    Map<Integer, Integer> processes = new HashMap<>();
    List<Entry<Integer, Integer>> readylist = new ArrayList<>(processes.entrySet());
                     
    while(!quelist.isEmpty()) {  //start
        for(int j: quelist){      //traverse que list
            if(process[j].isEmpty()){
                break;
            }
            
            bt[j]=process[j].get(0);  //get cpu burst
            totalbt[j]=totalbt[j]+bt[j];
            if(timeReady[j]>clock){ 
                clock = timeReady[j];  
                at[j]=clock;
               wt[j]=clock - timeReady[j];//get waiting time
            }
     
            else {              
                at[j]=clock;
                if(clock<65)
                tr.add(at[j]);
                wt[j]=clock-timeReady[j];                     
            }
            
    clock = clock+bt[j];
    process[j].remove(0);
    if(process[j].isEmpty()){
        completefinaltime[j] = clock;    //final time
        ttr[j]= wt[j]+bt[j]+ioBurst[j];
    totalttr[j] = totalttr[j] + ttr[j];
        System.out.println(ttr[j]+" + ");
    wtTotal[j] = wtTotal[j]+ wt[j];
    System.out.println("process: "+j+"  arrives at:--> "+at[j]+"  with cpu burst-->"+bt[j]+"  complete finish time: = "+completefinaltime[j] );
    }
    else{
    
    ioBurst[j]=process[j].get(0);
    totalioBurst[j]=totalioBurst[j] + ioBurst[j];
    timeReady[j]=clock+ioBurst[j];
    process[j].remove(0);
    processes.put(j, timeReady[j]);//put hashtable values by key 1-8
    unsorted.add(timeReady[j]);
    System.out.println("process: "+j+"  arrives at:--> "+at[j]+"  with cpu burst-->"+bt[j]+" and ioburst: "+ioBurst[j]+"and ready at:"+timeReady[j]);
     ttr[j]= wt[j]+bt[j]+ioBurst[j];
     System.out.println(ttr[j]+" "+j+" ttr ");
    totalttr[j] = totalttr[j] + ttr[j];
    wtTotal[j] = wtTotal[j]+ wt[j];

        }
    
    System.out.println("total waiting time: "+wtTotal[j]+" current waiting time: = "+wt[j]);
    System.out.println("clock is now at "+clock);
        }
   Collections.sort(unsorted);
   System.out.println("timeready list:"+unsorted);
   System.out.println("quelist:"+quelist+"size:"+quelist.size());
   quelist.clear();
   
   for(int c=0;c<=unsorted.size()-1;c++){
        for (Integer key : getKeys(processes, unsorted.get(c))) {
          System.out.println("added to quelist-->"+key);
           quelist.add(key);
        } 
        
             }       
                                               
        unsorted.clear();
        System.out.println("before removal "+quelist);
        toBeRemoved.forEach(b -> {
        quelist.remove(b);
        while(quelist.contains(b))
         quelist.remove(b);
            });
        
        System.out.println("after removal: "+quelist);
    toBeRemoved.clear();

    } 
          
    
    double totalburst =0;
    for(int a=1;a<=8;a++){
              System.out.println("process "+a+" total waiting time: "+wtTotal[a]+" total ioburst "+totalioBurst[a] + "total burst time "+totalbt[a]+" ==>ttr = "+totalttr[a]);
              completefinaltimetotal = completefinaltimetotal + completefinaltime[a];
              avgtrtotal = avgtrtotal + tr.get(a-1);
              ttrtotal = ttrtotal + totalttr[a];
              totalwt = totalwt + wtTotal[a];
              totalburst +=totalbt[a];
              
          } 
    totalburst = (totalburst/clock) * 100;
        System.out.println("*********************************************");
          System.out.println("FCFS CPU UTILIZATION: "+(int)totalburst);
          System.out.println("Tw"+"\t Ttr"+"\t Tr");
          for(int c=1;c<=8;c++){
              System.out.println(wtTotal[c]+"\t"+totalttr[c]+"\t"+tr.get(c-1));
          }
          System.out.println("complete time average: "+completefinaltimetotal/8);
          System.out.println("tr average: "+(double)avgtrtotal/tr.size());
          System.out.println("ttr average is "+(double)ttrtotal/8);
          System.out.println("average waitng time wt = "+(double)totalwt/8);
       }

    

   
    
    

    private static Set<Integer> getKeys(
      Map<Integer, Integer> processes, int value) {

      return processes
              .entrySet()
              .stream()
              .filter(entry -> Objects.equals(entry.getValue(), value))
              .map(Map.Entry::getKey)
              .collect(Collectors.toSet());

  }
    
}
    
    


    
