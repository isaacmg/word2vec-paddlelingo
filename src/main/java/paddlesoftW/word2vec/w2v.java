package paddlesoftW.word2vec;

import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.InMemoryLookupCache;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.UimaSentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by agibsonccc on 10/9/14.
 */
public class w2v {
	 public WordVectors wvecs;
    public Word2Vec vec;
    InMemoryLookupCache cache;
    WeightLookupTable table;
    TokenizerFactory t;
    SentenceIterator iter;

    public w2v(int layer, int vecLen) throws Exception {
        cache = new InMemoryLookupCache();
        table = new InMemoryLookupTable.Builder()
                .vectorLength(vecLen)
                .useAdaGrad(false)
                .cache(cache)
                .lr(0.025f).build();
        t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());
        iter = UimaSentenceIterator.createWithPath(".\\docs\\txt\\stest.txt");
        vec = new Word2Vec.Builder()
                .minWordFrequency(5).iterations(1)
                .layerSize(layer).lookupTable(table)
                .stopWords(new ArrayList<String>())
                .vocabCache(cache).seed(42)
                .windowSize(5).iterate(iter).tokenizerFactory(t).build();
    }

    //train the model for given txt
    public void train(String txtpath) throws Exception {
        iter = UimaSentenceIterator.createWithPath(txtpath);
        t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());
        vec.setSentenceIter(iter);
        vec.setTokenizerFactory(t);
        vec.fit();
    }

    //write the trained model into txt
    public void writeVec(String pathname){
        try {
            WordVectorSerializer.writeWordVectors(vec, pathname);
        } catch (IOException e) {
            System.err.println("Failed to write W2V model");
        }
    }
    public void loadVec(File txt){
        try {
            wvecs =  WordVectorSerializer.loadTxtVectors(txt);

        } catch(IOException e) {
            System.out.print("Loading fail");

        }
    }
}
