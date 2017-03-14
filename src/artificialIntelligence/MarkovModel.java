package artificialIntelligence;

import java.util.*;

/**
 * Created by Ryan on 3/9/2017.
 */
public class MarkovModel {
    private int modelOrder;
    private Map<String, List<String>> model;
    private Set<String> names;

    protected MarkovModel(int modelOrder){
        this.modelOrder = modelOrder;
        model = new HashMap<>();
        names = new HashSet<>();
    }

    /**
     * Method uses the given name to build the markov model
     * @param name
     */
    protected void buildModel(String name) {
        names.add(name);
        name = "_" + name + "_";
        for (int m = 1; m < modelOrder; m++) {
            for (int i = 0; i < (name.length() - m); i++) {
                String temp = name.substring(i, i + m);
                String temp1 = name.substring(i + m, i + m + 1);
                if (!model.containsKey(temp)) {
                    List<String> list = new ArrayList();
                    list.add(temp1);
                    model.put(temp, list);
                } else {
                    List<String> list = model.get(temp);
                    list.add(temp1);
                    model.put(temp, list);
                }
            }
        }
    }

    /**
     * Method returns the List in the model map associated with the given value.
     * @param key
     * @return
     */
    protected List getValues(String key) {
        if (!model.containsKey(key))
            return null;
        else
            return model.get(key);
    }

    /**
     * Checks to see if a name is in the set of pre-existing names
     * @param name
     * @return
     */
    protected boolean checkNameNovelty(String name) {
        return names.contains(name);
    }
}
