package egenproj.WeatherApp.Entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Wind {
	@Id
	private String id;
private String speed;
private String degree;
public Wind() {
	this.id=UUID.randomUUID().toString();
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getSpeed() {
	return speed;
}
public void setSpeed(String speed) {
	this.speed = speed;
}
public String getDegree() {
	return degree;
}
public void setDegree(String degree) {
	this.degree = degree;
}
@Override
public String toString() {
	return "Wind [id=" + id + ", speed=" + speed + ", degree=" + degree + "]";
}

}
