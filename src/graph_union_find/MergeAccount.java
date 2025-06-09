package graph_union_find;

import java.util.*;

public class MergeAccount {
    public List<List<String>> mergeAccounts(List<List<String>> accounts){
        int size = accounts.size();
        UnionFind unionFind = new UnionFind(size);
        Map<String, Integer> emailAccountMap = new HashMap<>();
        //Build Union Find structure
        for(int i=0; i<size; i++){
            for(int j=1; j<accounts.get(i).size(); j++){ // get all email one by one
                if(emailAccountMap.containsKey(accounts.get(i).get(j))){
                    unionFind.union(emailAccountMap.get(accounts.get(i).get(j)), i); //
                }else{
                    emailAccountMap.put(accounts.get(i).get(j), i);
                }
            }
        }
        //Build top parent id wise emails
        Map<Integer, List<String>> idEmailGroup = new HashMap<>();
        for(Map.Entry<String, Integer> entry : emailAccountMap.entrySet()){
            int ultimateParent = unionFind.find(entry.getValue());
            idEmailGroup.putIfAbsent(ultimateParent, new ArrayList<String>());
            idEmailGroup.get(ultimateParent).add(entry.getKey());
        }
        //Build name, asc order emails in a single list in the response
        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<Integer, List<String>> entry : idEmailGroup.entrySet()){
            List<String> nameEmailList = new ArrayList<>();
            nameEmailList.add(accounts.get(entry.getKey()).get(0));
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            nameEmailList.addAll(emails);
            result.add(nameEmailList);
        }
        return result;
    }

    public static void main(String[] args) {
        String[][] accounts = {{"neet","neet@gmail.com","neet_dsa@gmail.com"},
                                {"alice","alice@gmail.com"},
                                {"neet","bob@gmail.com","neet@gmail.com"},
                                {"neet","neetcode@gmail.com"}};
        List<List<String>> input = Arrays.stream(accounts).map(strArray -> Arrays.stream(strArray).toList()).toList();
        List<List<String>> outPut = new MergeAccount().mergeAccounts(input);
        System.out.println(outPut);
    }
}
