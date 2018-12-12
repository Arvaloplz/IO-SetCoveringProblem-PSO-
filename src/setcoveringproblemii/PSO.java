/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setcoveringproblemii;


import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Arrays;


public class PSO {
        private final double[] costs;
        int particulas=50;
        private final ArrayList<Particle> particles = new ArrayList<Particle>();
        Area area =new Area();
        private float bestFit = 999999999; //no entiendo por que tan grande
        private double[] inicialSolution = new double[]{0,0,1,0,1,0,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1};//REVISAR misma logica de costo (0-36)



    public PSO() {
       inicializarComunas();
        costs=new double[] {0,0,1,2,1.2,1.5,3,2,1,1,3,4,3,3,2,2.5,1.5,2,2,3,2,2,3,2,3,3,1,2.5,2,3.5,3,1.5,0,2,2,3.5,2,2.5,1.5};
        //se pone 0 en algunas posisiones para que quede ordenado el costo con el numero de comuna

    }

    public void iniciar(){
        /***
         *  CICLO DE ## 10 ## ITERACIONES
         */
        for(int i=0;i<10; i++){
            Particle particle = new Particle (bestFit,inicialSolution, area, costs);
            particles.add(particle);

            for(int j=0; j < 10; j++){
            for(Particle p : particles){
                p.calcularFitnessyActualizarMejorSolucion();
            }

            actualizarMejorSolucion();

            for(Particle p : particles)
            {
                p.actualizarVelocidadyActualizarPosicion(inicialSolution);
            }

            System.out.println("Mejor Solucion:\n" + Arrays.toString(inicialSolution));
            System.out.println("Mejor Fitness: " + bestFit);
            System.out.println("\n");

        }
            

        }
        


    }
        public void actualizarMejorSolucion() {
        double aux;
        for(Particle p : particles)
        {
            double auxFit = p.getFitness();
            if(auxFit < bestFit)
            {
                for(int i = 0; i < 36; i++)
                {
                    aux = p.getMSolucion()[i];
                    inicialSolution[i] = aux;
                }
                bestFit = (float) auxFit;
            }
        }
    }
    /***############################################################################################################
     *
     * inicializamos las entradas de las area colindantes.
     *
     * recordar que estas son a su vez las restricciones donde en cada conjunto se debe
     * seleccionar a lo menos una antena.
     *
     */
    private void inicializarComunas() {
      int[] newArea= new int[]{2,3,4,28};
      area.addArea(newArea);
      newArea = new int[]{2,3,4,5,28,29};
      area.addArea(newArea);
      newArea =new int[]{2,3,4,25,26,28};
      area.addArea(newArea);
      newArea =new int[]{3,5,27,29};
      area.addArea(newArea);
      newArea =new int[]{6,7,9,15,24,27};
      area.addArea(newArea);
      newArea =new int[]{6,7,8,9,10,15};
      area.addArea(newArea);
      newArea =new int[]{7,8,10};
      area.addArea(newArea);
      newArea =new int[]{6,7,9};
      area.addArea(newArea);
      newArea =new int[]{7,8,10,15,33};//10
      area.addArea(newArea);
      newArea =new int[]{11,12,13,15,16,17,24,25};//11
      area.addArea(newArea);
      newArea =new int[]{11,12,13,15,17};//12
      area.addArea(newArea);
      newArea =new int[]{11,12,13,15,17,33};//13
      area.addArea(newArea);
      newArea =new int[]{14,16,17,31,34,37,38};//14
      area.addArea(newArea);
      newArea =new int[]{6,7,10,11,12,13,15,24,34};//15
      area.addArea(newArea);
      newArea =new int[]{11,14,16,17,34};//16
      area.addArea(newArea);
      newArea =new int[]{11,12,13,14,16,17,31,33,35};//17
      area.addArea(newArea);
      newArea =new int[]{18,20,30};//18
      area.addArea(newArea);
      newArea =new int[]{19,21,22,30};//19
      area.addArea(newArea);
      newArea =new int[]{18,20,21,30};//20
      area.addArea(newArea);
      newArea =new int[]{19,20,21,30};//21
      area.addArea(newArea);
      newArea =new int[]{19,22,23};//22
      area.addArea(newArea);
      newArea =new int[]{22,23};//23
      area.addArea(newArea);
      newArea =new int[]{6,11,15,24,25,26,27,28};//24
      area.addArea(newArea);
      newArea =new int[]{4,11,24,25,26};//25
      area.addArea(newArea);
      newArea =new int[]{4,24,25,26,28};//26
      area.addArea(newArea);
      newArea =new int[]{5,6,24,27,28,29};//27
      area.addArea(newArea);
      newArea =new int[]{2,3,4,24,26,27,28,29};//28
      area.addArea(newArea);
      newArea =new int[]{3,5,27,28,29};//29
      area.addArea(newArea);
      newArea =new int[]{18,19,20,21,30,34,36};//30
      area.addArea(newArea);
      newArea =new int[]{14,17,31,34,35,38};//31
      area.addArea(newArea);
      newArea =new int[]{10,13,15,17,33,35};//33
      area.addArea(newArea);
      newArea =new int[]{14,16,30,31,34,36,37,38};//34
      area.addArea(newArea);
      newArea =new int[]{17,31,33,35};//35
      area.addArea(newArea);
      newArea =  new int[]{30,34,36,38};//36
      area.addArea(newArea);
      newArea =new int[]{14,34,37};//37
      area.addArea(newArea);
      newArea = new int[]{14,31,34,36,38};//38
      area.addArea(newArea);
    }



}
