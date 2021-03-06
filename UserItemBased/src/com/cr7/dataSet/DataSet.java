package com.cr7.dataSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cr7.util.Util;

public class DataSet {
	int ITEMNUM=1682;	//直接给出，也可从文件读取但是比较麻烦
	int USERNUM=943;
//	int ITEMNUM=2000;	//直接给出，也可从文件读取但是比较麻烦
//	int USERNUM=3118;
	public int testUserNum = 0;
	byte [][] rateMatrix;//用户评分数据，评分>=3的设为1，其他设为0
	private double ave[];	//用户的评分均值
	
	public DataSet(String file){
		rateMatrix = new byte[USERNUM+1][ITEMNUM+1];
		Util.initMatrix(rateMatrix);
		readRecord(file);
	}
	
	//读取评分矩阵
	private void readRecord(String rateFile){
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(rateFile));
			String line = "";
			int u=0;	//user
			int i=0;	//item
			byte s=0;	//score
			while((line=bf.readLine())!=null){
				Pattern p = Pattern.compile("[\\w]+");
				Matcher m = p.matcher(line);
				if(m.find()) u=Integer.parseInt(m.group());
				if(m.find()) i=Integer.parseInt(m.group());
				if(m.find()) s=Byte.parseByte(m.group());
				rateMatrix[u][i]=s;
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	
	public double [] getAverage(){
		ave = new double[USERNUM+1];
		Util.init(ave);
		for(int u=1;u<=USERNUM;u++){
			int count=0;
			for(int i=1;i<=ITEMNUM;i++){
				if(rateMatrix[u][i]!=0){
					ave[u]+=rateMatrix[u][i];
					count++;
				}
			}
			ave[u]/=((double)count);
		}
		return ave;
	}
	public byte [][] getRateMatrix(){
		return this.rateMatrix;
	}
	public int getITEMNUM() {
		return ITEMNUM;
	}
	public int getUSERNUM() {
		return USERNUM;
	}
}
