/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package setcoveringproblemii;
/**
 *
 * @author arval
 */
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
public class particle {
    Area newArea=new Area();
    private double[] costs;
    
    private double[] xSolution = {0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,1,1,0,1};
    private double[] position = {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1};
    private double[] velocidad = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private double xFitness;

    private final double vMax = 6;
    //private final double inercia = 0.07;
    private  double c1 = 0.05;
    private  double c2 = 0.05;


     public particle(float bestFit,double[] bestSolution,Area area,double[] costs) {

       double[] positionAux;
       positionAux=generateNewPosition();
       this.position=adjustPosition(positionAux);
       this.newArea=area;
       this.xFitness=bestFit;
       this.xSolution=bestSolution;
       this.costs=costs;

     }
    private double[] generateNewPosition() {
      double[] newPosition = new double[36];
      newPosition[0]=0;
      newPosition[1]=0;

          for(int i = 2; i < 37; i++)
          {
            if(i==32){
              newPosition[i]=0;
            }else{
                if(Math.random() < vMax)
                {
                    newPosition[i] = 1;
                } else {
                    newPosition[i] = 0;
                }
            }
          }
          return newPosition;
    }

    private double[] adjustPosition(double[] positionAux) {
        //LO VOY A HACER EN UNA CLASE APARTE
        //HAY QUE MODIFICARLO
        int[] vecinos;
        ArrayList<Integer> contemplados = new ArrayList<Integer>();
        ArrayList<Integer> exception = new ArrayList<Integer>();
        boolean esta = false;
        double costomenor = 9;
        int indicecostomenor = 0;

      while(true)
      {
          for(int i = 0; i < 36; i++)
          {
              if(positionAux[i] == 1)
              {
                  vecinos = (int[]) newArea.getComunaN(i);
                  for(int j = 0; j < vecinos.length; j++)
                  {
                      if(!contemplados.contains(vecinos[j]))
                      {
                          contemplados.add(vecinos[j]);
                      }
                  }
              }
          }

          exception.clear();
          for(int i = 2; i < 38; i++)
          {
              esta = false;
              for(int j = 0; j < contemplados.size(); j++)
              {
                  if(contemplados.get(j) == i)
                  {
                      esta = true;
                      break;
                  }
              }
              if(esta == false)
              {
                  exception.add(i);
              }
          }

          if(contemplados.size() == 36)
          {
              break;
          } else {
              for(int i = 0; i < newArea.getSize(); i++)
              {
                  vecinos = (int[]) newArea.getComunaN(i);
                  for(int j = 0; j < exception.size(); j++)
                  {
                      costomenor = 9;
                      for(int f = 0; f < vecinos.length; f++)
                      {
                          if(exception.get(j) == vecinos[f])
                          {
                              if(costs[i] < costomenor)
                              {
                                  indicecostomenor = i;
                                  costomenor = costs[i];
                              }
                          }
                      }
                      positionAux[indicecostomenor] = 1;
                  }
              }
          }
      }

      return positionAux;
          }

}
