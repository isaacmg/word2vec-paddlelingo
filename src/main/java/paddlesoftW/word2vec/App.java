package paddlesoftW.word2vec;

import java.io.File;
import java.util.Collection;


public class App 
{
	public static final int layer = 120, vecLen = 150;
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        w2v w2vmodel = new w2v(layer, vecLen);

		File modelfile = new File(".\\docs\\w2vmodel\\w2vmodelS.txt");
		boolean trained = true;
		boolean writemodel = true;
		//train the model if no model available
		
			w2vmodel.loadVec(modelfile);
			
			
			//w2vmodel.train(".\\docs\\txt\\gdi.txt");
			//w2vmodel.train(".\\docs\\txt\\results.txt");
			//w2vmodel.train(".\\docs\\txt\\dagger.txt");
			//w2vmodel.train(".\\docs\\txt\\ma.txt");
			//w2vmodel.train(".\\docs\\txt\\aa.txt");
			//Word2Vec tests 
			
			Collection<String> lst = w2vmodel.wvecs.wordsNearest("eddy", 10);
			Collection<String> lst3 = w2vmodel.wvecs.wordsNearest("creek", 10);
			Collection<String> lst5 = w2vmodel.wvecs.wordsNearest("kayak", 10);
			Collection<String> lst6 = w2vmodel.wvecs.wordsNearest("wave", 10);
			Collection<String> lst1 = w2vmodel.wvecs.wordsNearest("swim", 10);
			Collection<String> lst0 = w2vmodel.wvecs.wordsNearest("hole", 10);
			Collection<String> lst2 = w2vmodel.wvecs.wordsNearest("hole", 10);
			System.out.println("Words nearest eddy");
			
	        System.out.println(lst);
	        System.out.println(lst3);
	        System.out.println(lst5);
	        System.out.println(lst6);
	        System.out.println(lst0);
	        System.out.println(lst1);
			System.out.println(w2vmodel.wvecs.similarity("river", "creek"));
			System.out.println(w2vmodel.wvecs.similarity("hole", "sticky"));
			System.out.println(w2vmodel.wvecs.similarity("hole", "swim"));
			
			

			

			//can choose to write the trained model into txt
			if (writemodel) {
				//w2vmodel.writeVec(".\\docs\\w2vmodel\\w2vmodelS.txt");
			}
		
    }
}
