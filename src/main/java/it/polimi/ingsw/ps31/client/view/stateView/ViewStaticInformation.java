package it.polimi.ingsw.ps31.client.view.stateView;

/**
 * Created by giulia on 08/06/2017.
 */
public class ViewStaticInformation {
    private static int Family_Member_Number = 4;
    private static int Personal_Board_Identical_Box_Max= 6;
    private static int Personal_Board_Card_List_Number = 4;
    private static int Tower_Identical_Box_Max= 4;
    private static int Number_Of_Tower=4;
    private static int Max_Player_Name_Len=5;

    public static int getFamily_Member_Number() {
        return Family_Member_Number;
    }

    public static int getPersonal_Board_Identical_Box_Max() {
        return Personal_Board_Identical_Box_Max;
    }

    public static int getPersonal_Board_Card_List_Number() {
        return Personal_Board_Card_List_Number;
    }

    public static int getTower_Identical_Box_Max() {
        return Tower_Identical_Box_Max;
    }

    public static int getNumber_Of_Tower() {
        return Number_Of_Tower;
    }

    public static int getMax_Player_Name_Len() {
        return Max_Player_Name_Len;
    }
}
