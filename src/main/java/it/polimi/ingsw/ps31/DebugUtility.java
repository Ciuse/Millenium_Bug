package it.polimi.ingsw.ps31;

/**
 * Created by Francesco on 07/07/2017.
 *
 * Classe che semplifica il debug a console stampando, oltre al messaggio, la classe e il metodo chiamante.
 * Dopo aver rilasciato il progetto, è sufficiente impostare correttamente il booleano di questa classe per
 * impedire che vengano stampati i messaggi non destinati all'utente finale
 */
public class DebugUtility {
    /** Indica se stampare o meno il caller davanti ai messaggi destinati all'utente finale */
    private static boolean released = false;

    /** Indica se debuggare o meno i messaggi già indicati come debuggati */
    private static boolean hardDebug = false;


    /** Stampa a console i messaggi indicando il caller del metodo di stampa (il punto dove si vuole debuggare).
     * La posizione del chiamante nello stack non è fissata per consentire chiamate multiple all'interno di questa
     * classe stampando solo le informazioni relative al metodo interessato*/
    private static void dynamicStackDebugger(String msgToConsole, int stackPosition, boolean lfCr)
    {
        //Caller
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StackTraceElement caller = callStack[stackPosition];

        //Class name
        String[] packagesStack = caller.getClassName().split("\\.");
        String realClassName = packagesStack[packagesStack.length-1];

        //Method name
        String methodName = caller.getMethodName();

        //Print to console
        String strToConsole="";
        if( !released ){
            strToConsole +=  realClassName + ":" + methodName + ">";
        }
        strToConsole += msgToConsole;
        if( lfCr )
            System.out.println(strToConsole);
        else
            System.out.print(strToConsole);
    }

    /** Da usare per i messaggi destinati all'utente finale. Stampa il caller in base a released*/
    public static void simpleUserMessage(String msgToConsole)
    {
        dynamicStackDebugger(msgToConsole, 3, true);
    }

    /** Da usare per i messaggi da stampare solo durante il debug */
    public static void simpleDebugMessage(String msgToConsole)
    {
        if( released )
            return;
        dynamicStackDebugger(msgToConsole, 3, true);
    }

    /** Da usare per i messaggi destinati all'utente finale e di cui non interessa fare il debug
     *  Viene stampato il caller solo se hardDebug è impostato a true */
    public static void debuggedUserMessage(String msgToConsole) {
        if( hardDebug )
            dynamicStackDebugger(msgToConsole, 3, true);
        else
            System.out.println(msgToConsole);
    }

    /** Overload del precedente che consente di decidere se inserire un ritorno a capo in fondo al messaggio */
    public static void debuggedUserMessage(String msgToConsole, boolean lfCr) {
        if( hardDebug )
            dynamicStackDebugger(msgToConsole, 3, lfCr);
        else{
            if( lfCr )
                System.out.println(msgToConsole);
            else
                System.out.print(msgToConsole);
        }
    }

    /** Stampa a console il metodo che ha invocato il metodo in cui è stata richiamata quesa istruzione */
    public static String getCaller()
    {
        //Caller
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StackTraceElement caller = callStack[3];

        //Class name
        String[] packagesStack = caller.getClassName().split("\\.");
        String realClassName = packagesStack[packagesStack.length-1];

        //Method name
        String methodName = caller.getMethodName();

        return realClassName+":"+methodName;
    }
}
