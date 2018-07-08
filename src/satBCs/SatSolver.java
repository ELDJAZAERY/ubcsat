package satBCs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class SatSolver {
	
	private static int maxVar;
	private static String pathBCs;
	private static String nonBut;
	private static ArrayList<Integer> but = new ArrayList<>();
	private static ArrayList<String> BCs = new ArrayList<>();
	
	public static String ResAffiche;
	
	public static String getPathBCs() {return pathBCs;}

	public static void setPathBCs(String pathBCs) throws Exception {
		SatSolver.pathBCs = pathBCs;
		redBCs();
	}
	
	public static boolean pathVide(){return pathBCs.length()==0;}

	
	public static String getNonBut() {return nonBut;}

	public static boolean setFormul(String but) throws Exception{
		if(pathVide() || !butValide(but)){ ResAffiche="Une des variables constituant le but n'appartient pas aux variables de la BC !!! "; return false; }
		
		SatSolver.nonBut = nonBut(but);
		sauvg_BCs_absurde();
		execute_absurde();
		return true;
	}

	
	private static String nonBut(String but){
		String nonbut="";
		for(Integer i:SatSolver.but)
			if(i!=0) nonbut+=""+(i * -1 )+" 0\n";
		
		return nonbut;
	}
	
	private static boolean butValide(String but){
		SatSolver.but.clear();
		for(String s:but.split(" "))
			if(Integer.parseInt(s)>maxVar || Integer.parseInt(s)<-maxVar) return false;
			else SatSolver.but.add(Integer.parseInt(s));
		
		return true;	
	}
	
	
	private static void redBCs() throws Exception{
		BufferedReader bufR = new BufferedReader(new FileReader(pathBCs));
		
		String ligne;
		BCs.clear();
		
		while( (ligne = bufR.readLine()) != null ){
			// si la ligne courante n'est pas un commentaire
			if(ligne.charAt(0)!='c') BCs.add(ligne);
		}
		
		String[] confs = BCs.get(0).split(" ");
		maxVar = Integer.parseInt(confs[2]);
		
		bufR.close();
	}
	
	
	private static int sauvg_BCs_absurde() throws Exception{
		File f = new File("BCs_Absurde.cnf");
			
		if(!f.exists()) f.createNewFile();
		else{
		  f.delete();
		  return sauvg_BCs_absurde();
		}
		FileWriter fwrite = new FileWriter(f);
		BufferedWriter buf = new BufferedWriter(fwrite);
		
		String[] confs = BCs.get(0).split(" ");
		
		// newNbClauses int 
		confs[3] = ""+( Integer.parseInt(confs[3]) + but.size()-1 ) ;

		String newConf="";
		for(String s:confs) newConf+=s+" ";
		
		
		buf.write(newConf+"\n");
		for(int i = 1 ; i<BCs.size();i++) buf.write(BCs.get(i)+"\n");
		buf.write(nonBut);
		
		buf.close();

		return 1;
	}
	
	private static void execute_absurde() throws Exception {
		ResAffiche="";
		
		Runtime run = Runtime.getRuntime();
		Process proc = run.exec("absurde.bat");
		proc.waitFor();
		
		BufferedReader bufR = new BufferedReader(new FileReader("res.txt"));
		
		
		String ligne;
		
		while( (ligne = bufR.readLine()) != null ){
			ResAffiche+=ligne+"\n";
		}

		
		bufR.close();
	}
	
}
