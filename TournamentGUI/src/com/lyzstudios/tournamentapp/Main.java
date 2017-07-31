package com.lyzstudios.tournamentapp;

import com.lyzstudios.people.Person;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        int option = -1;

        Set<School> schools = new HashSet<>();
        Set<Person> contacts = new HashSet<>();

        // creating a competitor, setting their scores, and calculating their gc score
//        Person per = new Person("Joshua", "Sanford");
//        Competitor comp = new Competitor();
//        comp.setEqualToPerson(per);
//
//        comp.setWeaponsPlace(2, 23.54f);
//        comp.setFormsPlace(1, 25.67f);
//        comp.setSparringPlace(3);
//
//        System.out.println("GC score: "+comp.calcGCScore());



        // creating divisions and adding competitors to it
//        Set<Divisions> divs = new HashSet<>();
//        Set<Rank> ranks = new HashSet<>();
//        ranks.add(Rank.WHITE);
//        ranks.add(Rank.YELLOW);
//        ranks.add(Rank.ORANGE);
//        Divisions div1 = new Divisions(Divisions.DivisionType.FORMS,ranks,0,7);
//
//        System.out.println(div1.getName());
//
//        ranks = new HashSet<>();
//        ranks.add(Rank.WHITE);
//        ranks.add(Rank.YELLOW);
//        ranks.add(Rank.ORANGE);
//        Divisions div2 = new Divisions(Divisions.DivisionType.FORMS,ranks,8,10);
//
//        System.out.println(div2.getName());
//
//        divs.add(div1);
//        divs.add(div2);
//
//        Person p1 = new Person("six", "year", "old");
//        p1.setBirthdate(2010,06,30);
//        System.out.println("p1 age: "+p1.getAge());
//
//        Person p2 = new Person("nine", "year", "old");
//        p2.setBirthdate(2007,04,01);
//        System.out.println("p2 age: "+p2.getAge());
//
//        Competitor c1 = new Competitor();
//        c1.setEqualToPerson(p1);
//        Competitor c2 = new Competitor();
//        c2.setEqualToPerson(p2);
//        c1.setRank(Rank.ORANGE);
//        c2.setRank(Rank.YELLOW);
//        Set<Competitor> comps = new HashSet<>();
//
//        comps.add(c1);
//        comps.add(c2);
//
//        for(Competitor c: comps){
//            for(Divisions d: divs){
//                if(c.getAge() >= d.getMinAge() && (c.getAge() <= d.getMaxAge()) &&
//                        d.getRanks().contains(c.getRank())){
//                    d.getCompetitors().add(c);
//                    break;
//                } else {
//                    System.out.println("Unable to add comp to div");
//                    System.out.println(c.getFullName());
//                }
//            }
//        }
//
//        System.out.println("Div 1 comps: ");
//        for(Competitor c: div1.getCompetitors()){
//            System.out.println(c.getFullName());
//        }
//        System.out.println("Div 2 comps: ");
//        for(Competitor c: div2.getCompetitors()){
//            System.out.println(c.getFullName());
//        }




//        Scanner scanner = new Scanner(System.in);
//
//        // run the program
//        while(option != 0){
//            printMenu();
//            option = scanner.nextInt();
//            System.out.println(option);
//            switch (option){
//                case 1:
//                    contacts.add(createContact());
//                    break;
//                case 12:
//                    for(Person c: contacts){
//                        c.printInfoToConsole();
//                    }
//                    break;
//                case 0:
//                    System.exit(1);
//                    break;
//                case 2:
//                    schools.add(createSchool());
//                    break;
//                case 21:
//                    for(School s: schools){
//                        s.printInfoToConsole();
//                    }
//                case 13:
//                    printEditContact();
//                    option = scanner.nextInt();
//                    switch (option){
//
//                    }
//
//            }
//        }
    }


    // command line method
    public static Person createContact(){
        Person comp = new Person();
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter competitors first name: ");
        comp.setFirstName(scan.nextLine());

        System.out.println("Enter competitor's last name: ");
        comp.setLastName(scan.nextLine());

        System.out.println("Contacts Rank: ");
        System.out.println("1. White");
        System.out.println("2. Yellow");
        System.out.println("3. Orange");
        System.out.println("4. Green");
        System.out.println("5. Red");
        System.out.println("6. Blue");
        System.out.println("7. Purple");
        System.out.println("8. Brown");
        System.out.println("9. Black");
        int option = scan.nextInt();
        switch(option){
            case 1:
                comp.setRank(Rank.WHITE);
                break;
            case 2:
                comp.setRank(Rank.YELLOW);
                break;
            case 3:
                comp.setRank(Rank.ORANGE);
                break;
            case 4:
                comp.setRank(Rank.GREEN);
                break;
            case 5:
                comp.setRank(Rank.RED);
                break;
            case 6:
                comp.setRank(Rank.BLUE);
                break;
            case 7:
                comp.setRank(Rank.PURPLE);
                break;
            case 8:
                comp.setRank(Rank.BROWN);
                break;
            case 9:
                comp.setRank(Rank.BLACK);
                break;
        }

        System.out.println("Gender: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        option = scan.nextInt();
        switch (option){
            case 1:
                comp.setGender("Male");
                break;
            case 2:
                comp.setGender("Female");
                break;
        }
        System.out.println("Birth year: ");
        int year = scan.nextInt();
        System.out.println("Birth month(Number): ");
        int month = scan.nextInt();
        System.out.println("Birth day: ");
        int day = scan.nextInt();
        comp.setBirthdate(year,month,day);



        return comp;
    }

    public static School createSchool(){
        School school = new School();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter school's name: ");
        school.setName(scan.nextLine());
        return school;
    }

    public static void printMenu(){
        System.out.println("0. Quit");
        System.out.println("1. Create a contact");
        System.out.println("12. View contacts");
        System.out.println("13. Edit contacts");
        System.out.println("2. Create a school");
        System.out.println("21. View schools");
    }

    public static void printEditContact(){
        System.out.println("0. Quit");
        System.out.println("1. Change contact's name");
        System.out.println("2. Change birthday");
        System.out.println("3. Change rank");
        System.out.println("4. Change school");
    }
}
