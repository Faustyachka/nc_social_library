///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.sociallibrary.library;
//
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.books.Books;
//import com.google.api.services.books.BooksRequestInitializer;
//import com.google.api.services.books.Books.Volumes.List;
//import com.google.api.services.books.model.Volume;
//import com.google.api.services.books.model.Volumes;
////import com.sociallibrary.icrud.*;
//
//import java.io.IOException;
//
///**
// *
// * @author mazafaka
// */
//public class AddLibrary
//{
//    private static final String APPLICATION_NAME = "Social_Library";
//    private static final String API_KEY ="AIzaSyCZsI9e4CfhOOOKQrBXaYB3OkXdu_Qq-3Q";
//
//    public static void queryGoogleBooks(JsonFactory jsonFactory, String query)
//            throws Exception
//    {
//        // Set up Books client.
//        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
//        .setApplicationName(APPLICATION_NAME)
//        .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
//        .build();
//
//        List volumesList = books.volumes().list(query);
//        volumesList.setFilter("ebooks");
//
//        // Execute the query.
//        Volumes volumes = volumesList.execute();
//        if (volumes.getTotalItems() == 0 || volumes.getItems() == null)
//        {
//        System.out.println("No matches found.");
//        return;
//        }
//        // Output results.
//        for (Volume volume : volumes.getItems())
//        {
//            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
//            System.out.println("==========");
//            System.out.println("Title: " + volumeInfo.getTitle());
//            System.out.println("Pages: " + volumeInfo.getPageCount());
//            System.out.println("Genre: " + volumeInfo.getCategories());
//
//            java.util.List<String> authors = volumeInfo.getAuthors();
//            if (authors != null && !authors.isEmpty())
//            {
//                System.out.print("Author(s): ");
//                for (int i = 0; i < authors.size(); ++i)
//                {
//                    System.out.print(authors.get(i));
//                    if (i < authors.size() - 1)
//                    {
//                    System.out.print(", ");
//                    }
//                }
//            System.out.println();
//            }
//            if (volumeInfo.getDescription() != null && volumeInfo.getDescription().length() > 0)
//            {
//                System.out.println("Description: " + volumeInfo.getDescription());
//            }
//
//            if (volumeInfo.getRatingsCount() != null && volumeInfo.getRatingsCount() > 0)
//            {
//                int fullRating = (int) Math.round(volumeInfo.getAverageRating().doubleValue());
//                System.out.print("User Rating: ");
//                for (int i = 0; i < fullRating; ++i)
//                {
//                    System.out.print("*");
//                }
//                System.out.println(" (" + volumeInfo.getRatingsCount() + " rating(s))");
//            }
//
//        }
//    }
//
//    public static void main(String[] args)
//    {
//        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//
//        String prefix = null;
//        String query = " Moby Dick";
//        if ("--author".equals(query)) {
//          prefix = "inauthor:";
//        }
//        else if ("--isbn".equals(query))
//        {
//          prefix = "isbn:";
//        }
//        else if ("--title".equals(query))
//        {
//          prefix = "intitle:";
//        }
//        else if (query.startsWith("--"))
//        {
//          System.err.println("Unknown argument: " + query);
//        }
//        if (prefix != null)
//        {
//        query = prefix + query;
//        }
//        try
//        {
//        queryGoogleBooks(jsonFactory, query);
//        return;
//        }
//        catch (IOException e)
//        {
//        System.err.println(e.getMessage());
//        }
//        catch (Throwable t)
//        {
//            t.printStackTrace();
//        }
//    }
//}
