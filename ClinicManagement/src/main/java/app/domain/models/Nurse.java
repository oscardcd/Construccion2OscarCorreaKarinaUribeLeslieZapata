package app.domain.models;

public class Nurse extends User {
   
   
    // Methods specific to Nurse
    public void assistProcedure(String procedure, String petName) {
        System.out.println(petName + " assisted in " + procedure + " for pet " + petName);
    }

    public void applyMedication(String medication, String petName, String dose) {
        System.out.println(petName + " applied " + dose + " of " + medication + " to pet " + petName);
    }
   
}
