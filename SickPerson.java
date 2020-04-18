package com.islam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public  class SickPerson {
    public final static int ACCESS_LOW = 0;
    public final static int ACCESS_MEDIUM = 1;
    public final static int ACCESS_HIGH = 2;
    public final static int MALE = 0;
    public final static int FEMALE = 1;

    private String birthDate;
    private int personType;
    private String basicInsurance;
    private String advanceInsurance;
    private String education;
    private String job;
    private String address;
    private caseHistory caseHistory;

    public SickPerson(String birthDate, int personType, String basicInsurance,
                      String advanceInsurance, String education, String job,
                      String address, SickPerson.caseHistory caseHistory) {
        this.birthDate = birthDate;
        this.personType = personType;
        this.basicInsurance = basicInsurance;
        this.advanceInsurance = advanceInsurance;
        this.education = education;
        this.job = job;
        this.address = address;
        this.caseHistory = caseHistory;
    }

    public void addSick(String sickType,int security,String doctor,String memoir){
        caseHistory.addSick(sickType)
                .changeSecurityLevel(sickType,security)
                .addDoctorName(doctor,sickType)
                .addMemoir(memoir,sickType);

    }
    public void addExamination(String sickType,doctor doctor,String offer,String drugs){
        if (IsAccess(sickType,doctor)){
            Date date = new Date();
            caseHistory.addOffer(doctor.getName(),sickType,offer)
                    .addDrugs(sickType,doctor.getName(),drugs)
                    .addExaminationDate(sickType,doctor.getName(),date.toString());
        }
    }
    public void changeDrugs(String sickType,doctor doctor,String drugs){
        if (IsAccess(sickType,doctor)){
            caseHistory.addDrugs(sickType,doctor.getName(),drugs);
            System.out.println("Successful!");
        }else System.out.println("Failed!");
    }
    public void changeOffer(doctor doctor,String sickType,String offer){
        if (IsAccess(sickType,doctor)){
            caseHistory.addOffer(sickType,doctor.getName(),offer);
            System.out.println("Successful!");
        }else System.out.println("Failed!");
    }
    public void changeDoctor(doctor doctorOld,doctor doctorNew,String sickType){
        if (IsAccess(sickType,doctorOld)){
            caseHistory.addDoctorName(doctorNew.getName(),sickType);
            System.out.println("Successful!");
        }else System.out.println("Failed!");

    }
    public void changeSecurity(String sickType,int security,doctor doctor){
        if (IsAccess(sickType, doctor)){
            caseHistory.changeSecurityLevel(sickType,security);
            System.out.println("Successful!");
        }else System.out.println("Failed!");
    }
    public void changeMemoir(String memoir,String sickType,doctor doctor){
        if (IsAccess(sickType,doctor)){
            caseHistory.addMemoir(memoir,sickType);
            System.out.println("Successful!");
        }else System.out.println("Failed!");

    }

    public void show(String sickType,doctor doctor){
        if (IsAccess(sickType, doctor)){
            System.out.println(toString());
            System.out.println(caseHistory.toString(sickType,doctor.getName()));
        }else {
            System.out.println(toString());
        }
    }

    @Override
    public String toString() {
        String person;
        if (personType == MALE)
            person = "Male";
        else
            person = "Female";
        return "SickPerson{" +
                "birthDate='" + birthDate + '\'' +
                ", personType=" + person +
                ", basicInsurance='" + basicInsurance + '\'' +
                ", advanceInsurance='" + advanceInsurance + "\n" +
                ", education='" + education + '\'' +
                ", job='" + job + '\'' +
                ", address='" + address + '\'' + '}';
    }

    private boolean IsAccess(String sickType, doctor doctor) {
        if (doctor.getAccess() >= caseHistory.getSecurityLevel().get(sickType))
            return true;
        return caseHistory.getDoctorsName().get(sickType).contains(doctor.getName());
    }

    private static class caseHistory implements com.islam.caseHistory {

        private ArrayList<String> sickType;
        private HashMap<String,String> personMemoir;
        private HashMap<String,ArrayList<String>> doctorOffer;
        private HashMap<String,Integer> securityLevel;
        private HashMap<String,ArrayList<String>> drugs;
        private HashMap<String,ArrayList<String>> examinationDate;
        private HashMap<String,ArrayList<String>> doctorsName;

        public caseHistory() {
            sickType = new ArrayList<>();
            personMemoir = new HashMap<>();
            doctorOffer = new HashMap<>();
            drugs = new HashMap<>();
            examinationDate = new HashMap<>();
            doctorsName = new HashMap<>();
            securityLevel = new HashMap<>();

        }

        SickPerson.caseHistory addDoctorName(String name,String sickType){
            doctorsName.get(sickType).add(name);
            return this;
        }
        SickPerson.caseHistory addSick(String sickType){
            this.sickType.add(sickType);
            securityLevel.replace(sickType,ACCESS_LOW);
            return this;
        }
        SickPerson.caseHistory addOffer(String doctorName,String sickType,String offer){
            int index = findDoctorId(doctorName,sickType);
            if (index == -1){
                System.out.println("Failed! at first add the doctor name");
                return this;
            }
            ArrayList<String> array =  doctorOffer.get(sickType);
            if (index == array.size())
                array.add(offer);
            else
                array.set(index,offer);
            doctorOffer.put(sickType,array);
            return this;
        }
        SickPerson.caseHistory addMemoir(String memoir,String sickType){
            personMemoir.replace(sickType,memoir);
            return this;
        }
        SickPerson.caseHistory changeSecurityLevel(String sickType,int security){
            securityLevel.replace(sickType,security);
            return this;
        }
        SickPerson.caseHistory addDrugs(String sickType,String doctorName,String drugs){
            int index = findDoctorId(doctorName,sickType);
            if (index == -1){
                System.out.println("Failed! at first add the doctor name");
                return this;
            }
            ArrayList<String> arr = this.drugs.get(sickType);
            if (index == arr.size())
                arr.add(drugs);
            else
                arr.set(index,drugs);
            this.drugs.replace(sickType,arr);
            return this;
        }
        SickPerson.caseHistory addExaminationDate(String sickType,String doctorName,String examination){
            int index = findDoctorId(doctorName,sickType);
            if (index == -1){
                System.out.println("Failed! at first add the doctor name");
                return this;
            }
            ArrayList<String> arr = this.examinationDate.get(sickType);
            if (index == arr.size())
                arr.add(examination);
            else
                arr.set(index,examination);
            this.examinationDate.replace(sickType,arr);
            return this;
        }
        private int findDoctorId(String doctorName, String sickType) {
            ArrayList<String> names = this.doctorsName.get(sickType);
            int i=0;
            for (String node:names) {
                if (doctorName.equals(node))
                    break;
                i++;
            }
            if (i == names.size()) return -1;
            return i;
        }
        public HashMap<String, Integer> getSecurityLevel() {
            return securityLevel;
        }
        public HashMap<String, ArrayList<String>> getDoctorsName() {
            return doctorsName;
        }
        public String toString(String sickType,String doctor) {
            return "caseHistory{" +
                    "sickType=" +  sickType+
                    "\n doctorsName=" + doctorsName.get(sickType) +
                    "\n personMemoir=" + personMemoir.get(sickType) +
                    "\n doctorOffer=" + doctorOffer.get(sickType) +
                    "\n drugs=" + drugs .get(sickType)+
                    "\n examinationDate=" + examinationDate.get(sickType) +
                    "\n securityLevel=" + securityLevel.get(sickType) +
                    '}';
        }
    }
}
