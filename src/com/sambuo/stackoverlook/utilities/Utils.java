package com.sambuo.stackoverlook.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.sambuo.stackoverlook.repositories.StackOverflowRepository;

public class Utils {
	
	/**
	 * Converts an {@link Iterable} to an array
	 * 
	 * @param iterable	The {@link Iterable} to convert
	 * @return			The converted array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Iterable<T> iterable)
    {
		if (iterable == null) {
			return null;
		}
		
        return (T[]) Utils.toArrayList(iterable).toArray();
    }
	
	/**
	 * Converts an {@link Iterable} to an {@link ArrayList}
	 * 
	 * @param iterable	The {@link Iterable} to convert
	 * @return			The converted {@link ArrayList}
	 */
	public static <T> ArrayList<T> toArrayList(Iterable<T> iterable) {
		
		if (iterable == null) {
			return null;
		}
		
		ArrayList<T> arrayList;
		if (iterable instanceof ArrayList<?>) {
			arrayList = (ArrayList<T>) iterable;
		}
		else {
			arrayList = new ArrayList<T>();
	        for(T item : iterable) {
	            arrayList.add(item);
	        }
		}
		
		return arrayList;
	}
	
	/**
	 * Reduces an {@link Iterable}
	 * Code taken from http://stackoverflow.com/questions/223013/is-there-a-type-safe-java-implementation-of-reduce
	 * 
	 * @param reducer	Class with function for reducing
	 * @param i			{@link Iterable} being reduced 
	 * @return			The reduced value
	 */
	public static <T> T reduce(final Reducer<T,T> reducer, 
	        final Iterable<? extends T> i)
	{
	    T result = null;
	    final Iterator<? extends T> iter = i.iterator();
	    if (iter.hasNext())
	    {
	        result = iter.next();
	        while (iter.hasNext())
	        {
	            result = reducer.foldIn(result, iter.next());
	        }
	    }
	    return result;
	}
	
	/**
	 * Reduces an {@link Iterable}
	 * Code taken from http://stackoverflow.com/questions/223013/is-there-a-type-safe-java-implementation-of-reduce
	 * 
	 * @param reducer		Class with function for reducing
	 * @param i				{@link Iterable} being reduced
	 * @param initializer	A seed value to start reducing with	
	 * @return				The reduced value
	 */
	public static <A,T> A reduce(final Reducer<A,T> reducer, 
	        final Iterable<? extends T> i, 
	        final A initializer)
	{
	    A result = initializer;
	    final Iterator<? extends T> iter = i.iterator();
	    while (iter.hasNext())
	    {
	        result = reducer.foldIn(result, iter.next());
	    }
	    return result;
	}
	
	/**
	 * Grabs text from a URL containing gzipped content.
	 * 
	 * Code from http://www.vogella.com/articles/AndroidJSON/article.html
	 * and http://stackoverflow.com/questions/2888647/json-url-from-stackexchange-api-returning-jibberish
	 * 
	 * @param url	A url that returns gzipped content
	 * @return		The ungzipped content
	 */
	public static String getJSONfromStackOverflowURL(String url) {
		StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet(url);
	    try {
	    	HttpResponse response = client.execute(httpGet);
	    	StatusLine statusLine = response.getStatusLine();
	    	int statusCode = statusLine.getStatusCode();
	    	if (statusCode == 200) {
	    		HttpEntity entity = response.getEntity();
	    		InputStream content = entity.getContent();
	    		BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(content)));
	    		String line;
	    		while ((line = reader.readLine()) != null) {
	    			builder.append(line);
	    		}
	    	} else {
	    		Log.e(StackOverflowRepository.class.toString(), "Failed to download file");
	    	}
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    return builder.toString();
	}
	
	public static String getJsonStringSafe(JSONObject object, String name) {
		String s = null;
		
		try {
			s = object.getString(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public static long getJsonLongSafe(JSONObject object, String name, long seed) {
		long l = seed;
		
		try {
			l = object.getLong(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return l;
	}
	
	public static int getJsonIntSafe(JSONObject object, String name, int seed) {
		int i = seed;
		
		try {
			i = object.getInt(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
	public static boolean getJsonBooleanSafe(JSONObject object, String name, boolean seed) {
		boolean b = seed;
		
		try {
			b = object.getBoolean(name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return b;
	}
}
