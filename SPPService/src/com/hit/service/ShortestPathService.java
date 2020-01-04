package com.hit.service;

import java.io.IOException;

import com.hit.algorithm.IAlgoSPP;
import com.hit.graph.AbstarctWeightedGraph;
import com.hit.graph.IGraph;
import com.hit.idao.DaoFileImpl;

public class ShortestPathService {
	IAlgoSPP<Integer, Integer> algo;	
	String filePath = ".//src\\resources\\datasource.json";
	DaoFileImpl<IGraph<Integer,Integer>> idao = new DaoFileImpl<IGraph<Integer,Integer>>(filePath);
	
	public ShortestPathService(IAlgoSPP<Integer, Integer> algo) {
		this.algo = algo;
	}
	
	public ShortestPathService(IAlgoSPP<Integer, Integer> algo,String filepath) {
		this.algo = algo;
		this.filePath = filepath;
	}
	
	public DaoFileImpl<IGraph<Integer,Integer>> getDao() {
		return idao;
	}
	
	public Integer compute(AbstarctWeightedGraph<Integer,Integer> graph, Integer source, Integer dest) {
		try {	
			return algo.compute(graph, source, dest);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
