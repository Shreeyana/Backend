//package nce.majorproject.recommendation;
//
//public class RecommendationHelper {
//    public static double cosineSimilarity(int[] vector_x, int[] vector_y){
//        float dot_pdt = 0;
//        double length_x = 0;
//        double length_y = 0;
//
//        for (int i=0;i<vector_x.length;i++) {
//            dot_pdt += vector_x[i] * vector_y[i];
//            length_x += Math.pow(vector_x[i], 2);
//            length_y += Math.pow(vector_y[i],2);
//        }
//        length_x = Math.sqrt(length_x);
//        length_y = Math.sqrt(length_y);
//        double den;
//        den = length_x * length_y;
//
//        if (den == 0) {
//        return 0;
//        }
//        else{
//        return dot_pdt /(den);
//        }
//    }
//    public static void buildItemVector(Object Item){
//        vector = []//list
//        genres = []//list
//        for genre in movie['genres']://movie[genres]=find movie genre
//        genres.append(genre['name'])
//
//        for genre in self.genres:
//        if genre in genres:
//        vector.append(1)
//            else:
//        vector.append(0)
//
//        return vector
//    }
//    public static double getSimilarityBetweenItems(Object Item1, Object Item2){
//        vector_x = buildItemVector(Item1);
//        vector_y = buildItemVector(Item2);
//
//        return cosineSimilarity(vector_x, vector_y);
//    }
//    https://github.com/amitab/Recommendare/blob/master/moviesimilarity.py
//}
