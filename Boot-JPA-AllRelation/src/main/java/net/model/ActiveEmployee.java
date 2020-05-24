package net.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity  
public class ActiveEmployee extends Employee {  
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -8047689041220268193L;
	
	private BigDecimal salary;  
    private int experience;  
    
    public ActiveEmployee(String name, BigDecimal salary, int experience) {
		super(name);
		this.salary = salary;
		this.experience = experience;
	}

	public ActiveEmployee() {  
        super();  
          
    }

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "\n ActiveEmployee [salary=" + salary + ", experience=" + experience + "]";
	}  
      
      
      
}  
