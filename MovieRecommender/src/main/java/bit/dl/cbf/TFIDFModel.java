package bit.dl.cbf;

import org.grouplens.grapht.annotation.DefaultProvider;
import org.grouplens.lenskit.core.Shareable;
import org.grouplens.lenskit.vectors.MutableSparseVector;
import org.grouplens.lenskit.vectors.SparseVector;

import java.io.Serializable;
import java.util.Map;



@DefaultProvider(TFIDFModelBuilder.class)
public class TFIDFModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> tagIds;
    private final Map<Long, SparseVector> itemVectors;

    TFIDFModel(Map<String,Long> tagIds, Map<Long,SparseVector> itemVectors) {
        this.tagIds = tagIds;
        this.itemVectors = itemVectors;
    }

    public MutableSparseVector newTagVector() {
        return MutableSparseVector.create(tagIds.values());
    }

    public SparseVector getItemVector(long item) {
        // Look up the item
        SparseVector vec = itemVectors.get(item);
        if (vec == null) {
            // We don't know the item! Return an empty vector
            return SparseVector.empty();
        } else {
            return vec;
        }
    }
}
