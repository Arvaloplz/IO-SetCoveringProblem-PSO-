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
    //
    private double[] xSolution = {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1};
    private double[] position = {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1};
    //esta saltado para que cumpla con el formato planteado de vector costo.
    //costs-> {!!!!!! 0,0 !!!!!!,1,2,1.2,1.5,3,2,1,1,3,4,3,3,2,2.5,1.5,2,2,3,2,2,3,2,3,3,1,2.5,2,3.5,3,1.5,!!!0!!!!,2,2,3.5,2,2.5,1.5};
    private double[] velocidad = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private double xFitness;

    //private final double vMax = 1;
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
                if(Math.random() < 6)
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
      /*
      while(true)
      {
          for(int i = 0; i < 36; i++)
          {
              if(x[i] == 1)
              {
                  vecinos = comunas.get(i);
                  for(int j = 0; j < vecinos.length; j++)
                  {
                      if(!contemplados.contains(vecinos[j]))
                      {
                          contemplados.add(vecinos[j]);
                      }
                  }
              }
          }

          excluidos.clear();
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
                  excluidos.add(i);
              }
          }

          if(contemplados.size() == 36)
          {
              break;
          } else {
              for(int i = 0; i < comunas.size(); i++)
              {
                  vecinos = comunas.get(i);
                  for(int j = 0; j < excluidos.size(); j++)
                  {
                      costomenor = 9;
                      for(int f = 0; f < vecinos.length; f++)
                      {
                          if(excluidos.get(j) == vecinos[f])
                          {
                              if(costos[i] < costomenor)
                              {
                                  indicecostomenor = i;
                                  costomenor = costos[i];
                              }
                          }
                      }
                      x[indicecostomenor] = 1;
                  }
              }
          }
      }*/

      return positionAux;
          }

}
