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
public class Particle{
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


     public Particle(float bestFit,double[] bestSolution,Area area,double[] costs) {

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

          for(int i = 0; i < 38; i++)
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

    private double[] mejorarFitness(double[] x) {
        int[] vecinos;
        ArrayList<Integer> contemplados = new ArrayList<Integer>();
        int cont = 0;
        double aux;
        double[] auxin = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        while(true)
        {
            for(int i = 0; i < 36; i++)
            {
                aux = x[i];
                auxin[i] = aux;
            }
            contemplados.clear();
            for(int i = cont; i < 36; i++)
            {
                if(auxin[i] == 1)
                {
                    auxin[i] = 0;
                    cont = i+1;
                    break;
                }

                if(i >= 35) cont = 36;
            }

            for(int i = 0; i < 36; i++)
            {
                if(auxin[i] == 1)
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
            
            if(contemplados.size() == 36) break;
            
            if(cont == 36)
            {
                auxin = x;
                break;
            }
            
        }
        x = auxin;
        
        return x;
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
          for(int i = 0; i < 38; i++)
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
    
     public void calcularFitnessyActualizarMejorSolucion() {
        double fit = calcularFitness();
        actualizarMejorSolucion(fit);
    }
     private void actualizarMejorSolucion(double fit) {
        if(fit < xFitness)
        {
            this.xFitness = fit;
            this.xSolution = position;
        }
    }
        private void actualizarVelocidad(double[] mejorsolucion) {
        for(int i = 0; i < velocidad.length; i++)
        {
            this.velocidad[i] = 0.5*velocidad[i] + c1*Math.random()*(xSolution[i] - position[i]) + c2*Math.random()*(mejorsolucion[i] - position[i]);//*********
            if(Math.abs(velocidad[i]) >= 1)
            {
                if(velocidad[i] < 0)
                {
                    velocidad[i] = -1;
                } else {
                    velocidad[i] = 1;
                }
            }
        }
        //System.out.println(Arrays.toString(velocidad));
    }
    
     public void actualizarVelocidadyActualizarPosicion(double[] mejorsolucion) {
        actualizarVelocidad(mejorsolucion);
        actualizarPosicion();
        //pos = generarSolucionAleatoria();
    }
     private double calcularFitness() {
        double sum = 0;
        for(int i = 0; i < 36; i++)
        {
            sum += position[i] * costs[i];
        }
        return sum;
    }
    
    private void actualizarPosicion() {
        int[] vecinos;
        ArrayList<Integer> contemplados = new ArrayList<Integer>();
        double sig;
        double[] aux;
        while(true)
        {
            contemplados.clear();
            aux = position;
            for(int i = 0; i < 36; i++)
            {
                sig = 1/(1+(Math.exp(-velocidad[i])));
                if(Math.random() < sig)
                {
                    if(aux[i] == 1)
                    {
                        aux[i] = 0;
                    } else {
                        aux[i] = 1;
                    }
                }

            }

            for(int i = 0; i < 38; i++)
            {
                if(aux[i] == 1)
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

            if(contemplados.size() == 36) break;
        }
        
        for(int i = 0; i < 36; i++)
        {
            aux =mejorarFitness(aux); // Búsqueda local
        }
        
        position = aux;

    }
          //valor de el costo minimizado por iteracion
  public double calcularEsteFitness(double[] aux) {
        double sum = 0;
        for(int i = 0; i < 38; i++)
        {
            sum += aux[i] * costs[i];
        }
        return sum;
  }
      public double[] getMSolucion() {
        return xSolution;
    }

    public double getFitness() {
        return xFitness;
    }

}
