package bit.dl.cbf;



import org.grouplens.lenskit.ItemRecommender;
import org.grouplens.lenskit.ItemScorer;
import org.grouplens.lenskit.Recommender;
import org.grouplens.lenskit.RecommenderBuildException;
import org.grouplens.lenskit.core.LenskitConfiguration;
import org.grouplens.lenskit.core.LenskitRecommender;
import org.grouplens.lenskit.data.dao.EventDAO;
import org.grouplens.lenskit.data.dao.ItemDAO;
import org.grouplens.lenskit.data.dao.UserDAO;
import org.grouplens.lenskit.scored.ScoredId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bit.dl.cbf.dao.*;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ContentBasedMain {
    private static final Logger logger = LoggerFactory.getLogger(ContentBasedMain.class);
    
    public static void main(String[] args) throws RecommenderBuildException {
    	
    	
    	
        LenskitConfiguration config = configureRecommender();

        logger.info("building recommender");
        Recommender rec = LenskitRecommender.build(config);
      

//        if (args.length == 0) {
//            logger.error("No users specified; provide user IDs as command line arguments");
//        }

        ItemRecommender irec = rec.getItemRecommender();
        assert irec != null;
        
        new ContBasedReomSysInterfaceTest(irec);
        
    }

    @SuppressWarnings("unchecked")
    private static LenskitConfiguration configureRecommender() {
        LenskitConfiguration config = new LenskitConfiguration();
        // configure the rating data source
        config.bind(EventDAO.class)
              .to(RatingDAO.class);
        config.set(RatingFile.class)
              .to(new File("data/ratings.csv"));

        // use custom item and user DAOs
        // specify item DAO implementation with tags
        config.bind(ItemDAO.class)
              .to(CSVItemTagDAO.class);
        // specify tag file
        config.set(TagFile.class)
              .to(new File("data/movie-tags.csv"));
        // and title file
        config.set(TitleFile.class)
              .to(new File("data/movie-titles.csv"));

        config.bind(UserDAO.class)
              .to(UserDAO.class);
        config.set(UserFile.class)
              .to(new File("data/users.csv"));

        config.bind(ItemScorer.class)
              .to(TFIDFItemScorer.class);
        return config;
    }

}



