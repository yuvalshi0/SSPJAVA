package com.hit.service;

import java.io.IOException;
import java.util.LinkedList;

import com.hit.algorithm.IAlgoSPP;
import com.hit.graph.AbstarctWeightedGraph;

public class ShortestPathService {
	IAlgoSPP<Integer, Integer> algo;	
	String filePath = ".//src\\resources\\datasource.json";
	//DaoFileImpl<IGraph<Integer,Integer>> idao = new DaoFileImpl<IGraph<Integer,Integer>>(filePath);
	
	public ShortestPathService(IAlgoSPP<Integer, Integer> algo) {
		this.algo = algo;
	}
	
	public ShortestPathService(IAlgoSPP<Integer, Integer> algo,String filepath) {
		this.algo = algo;
		this.filePath = filepath;
	}
	
	
	public LinkedList<Integer> compute(AbstarctWeightedGraph<Integer,Integer> graph, Integer source, Integer dest) {
		try {	
			return algo.compute(graph, source, dest).asPath();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer computeAsSize(AbstarctWeightedGraph<Integer,Integer> graph, Integer source, Integer dest) {
		try {	
			return algo.compute(graph, source, dest).asValue();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
