
package android.desserts.gsondemo.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Employees {

    
	@SerializedName("employees")
    private List<Employee> employees = new ArrayList<Employee>();
    
	@SerializedName("company")
    private String company;
    
	@SerializedName("location")
    private String location;

    /**
     * 
     * @return
     *     The employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * 
     * @param employees
     *     The employees
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * 
     * @return
     *     The company
     */
    public String getCompany() {
        return company;
    }

    /**
     * 
     * @param company
     *     The company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 
     * @return
     *     The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

}
