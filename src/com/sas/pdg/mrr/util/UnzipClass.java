package com.sas.pdg.mrr.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class UnzipClass {

	public static ArrayList<String> unzipWeb(String location,String zipfilename){
	    FileInputStream fileInputStream = null;
	    ZipInputStream zipInputStream = null ;
	    ZipEntry entry = null;
	    
	    ArrayList<String> htmlfiles=new ArrayList<String>();
	    try {
	        fileInputStream = new FileInputStream(location + zipfilename + ".zip");
	        zipInputStream  = new ZipInputStream(fileInputStream);
	        
	        File dirfile = new File(location + zipfilename);
	        if(!dirfile.exists())
            {
	        	dirfile.mkdir();
            }

	        while((entry = zipInputStream.getNextEntry())!=null)//Check for more files available or not
	        {	
	            if(entry.isDirectory())//Check if this is Directory : yes then create Directory
	            {
	                //System.out.println("Createing Directory:"+entry.getName());
	                
	                String path = location + zipfilename + "/" + entry.getName();
	               
	                StringTokenizer st= new StringTokenizer(path,"/");
	                String path1=st.nextToken()+"/";
	                String path2 =path1;
	                while(st.hasMoreTokens())
	                {
	                      path1=st.nextToken()+"/";
	                      path2+=path1;
	                      File inbox  = new File(path2);
	                      if(!inbox.exists())
	                           inbox.mkdir();
	                }
	               
	            }
	        }
	        
	        fileInputStream = new FileInputStream(location + zipfilename + ".zip");
	        zipInputStream  = new ZipInputStream(fileInputStream);
	        while((entry = zipInputStream.getNextEntry())!=null)//Check for more files available or not
	        {	
	            if(!entry.isDirectory())//Check if this is Directory : yes then create Directory
	            {
	                //System.out.println("omtlab >>Unzipping File:"+entry.getName());
	                int size = 0;
	                byte[] buffer = new byte[2048];
	                FileOutputStream fileOutputStream = new FileOutputStream(location + zipfilename + "/" + entry.getName());
	                BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream,buffer.length);
	                while(  (size = zipInputStream.read(buffer, 0, buffer.length)) != -1)
	                {
	                    bos.write(buffer,0,size);
	                }
	                bos.flush();
	                bos.close();
	                if(entry.getName().endsWith(".html")||entry.getName().endsWith(".htm")){
	                	htmlfiles.add(zipfilename + "/" + entry.getName());
	                }else if(entry.getName().endsWith(".jsp")){
	                	File jspfile = new File(location + "/" + zipfilename + "/" + entry.getName());
	                	jspfile.delete();
	                } 
	            }
	        }	        
	        fileInputStream.close();
	        zipInputStream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return htmlfiles;
	}
	
	public static void main(String args[])
	{
	}
}

