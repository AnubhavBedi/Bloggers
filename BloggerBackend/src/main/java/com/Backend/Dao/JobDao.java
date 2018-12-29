
package com.Backend.Dao;

import java.util.List;

import com.Backend.ApplyJob;
import com.Backend.Job;


public interface JobDao {
	
	public boolean addJob(Job job);
	public boolean deleteJob(Job job);
	public boolean updateJob(Job job);
	public Job getJob(int jobId);
	public List<Job> listAlljobs();
	public boolean applyJob(ApplyJob applyJob);
	public List<ApplyJob> getAllAppliedjobsDetails();
}
