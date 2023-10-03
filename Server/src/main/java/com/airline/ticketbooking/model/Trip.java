package com.airline.ticketbooking.model;



import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
@Entity
public class Trip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "trip_generator")
	@SequenceGenerator(name = "trip_generator",sequenceName = "trip_seq", allocationSize = 1)
	private int id;
	private String departure;
	private String arrival;
	private Date depatureDate;
	private Date arrivalDate;
	private String duration;
	private int availableSeats;
	private boolean status;
	private int pricePerSeat;
	@CreationTimestamp
	private Timestamp createAt;
	
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @JsonBackReference
    private Flight flight;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="trip")
	private List<Reservation> reservation;
	
	public Trip() {
		super();
	}
	
	public Trip(int id, String departure, String arrival, Date depatureDate, Date arrivalDate,
			String duration, int availableSeats, boolean status,
			int pricePerSeat, Timestamp createAt) {
		super();
		this.id = id;
		this.departure = departure;
		this.arrival = arrival;
		this.depatureDate = depatureDate;
		this.arrivalDate = arrivalDate;
		this.duration = duration;
		this.availableSeats = availableSeats;
		this.status = status;
		this.pricePerSeat = pricePerSeat;
		this.createAt = createAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public Date getDepatureDate() {
		return depatureDate;
	}
	public void setDepatureDate(Date depatureDate) {
		this.depatureDate = depatureDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/*
	 * public Date getDepatureTime() { return depatureTime; } public void
	 * setDepatureTime(Date depatureTime) { this.depatureTime = depatureTime; }
	 * public Date getArrivalTime() { return arrivalTime; } public void
	 * setArrivalTime(Date arrivalTime) { this.arrivalTime = arrivalTime; }
	 */
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getPricePerSeat() {
		return pricePerSeat;
	}
	public void setPricePerSeat(int pricePerSeat) {
		this.pricePerSeat = pricePerSeat;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
}
