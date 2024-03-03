package se.ifmo.lab5.objects;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import se.ifmo.lab5.collection.format.csv.converter.AlbumConverter;
import se.ifmo.lab5.collection.format.csv.converter.CoordinatesConverter;
import se.ifmo.lab5.collection.format.csv.converter.LocalDateTimeConverter;
import se.ifmo.lab5.objects.exceptions.InvalidArgumentException;
import se.ifmo.lab5.objects.exceptions.NullableFieldException;

import java.time.LocalDateTime;

public class MusicBand implements Comparable<MusicBand> {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    @CsvBindByName(required = true)
    private Long id = System.currentTimeMillis();

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * set id of the band with validation
     * @return id of the band
     */
    public Long getId() {
        return id;
    }

    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @CsvBindByName(required = true)
    private String name;

    /**
     * set name of the band with validation
     * @param name - name of the band
     * @throws InvalidArgumentException when argument invalid (null or blank)
     */
    public void setName(String name) {
        if (name == null) throw new NullableFieldException("name");
        if (name.isBlank()) throw new InvalidArgumentException("name", "строка не может быть пустой");

        this.name = name;
    }

    /**
     * get name of the band
     * @return name of the band
     */
    public String getName() {
        return name;
    }

    /**
     * Поле не может быть null
     */
    @CsvCustomBindByName(required = true, converter = CoordinatesConverter.class)
    private Coordinates coordinates;

    /**
     * set coordinates of the band with validation
     * @param coordinates - coordinates of the band
     * @throws InvalidArgumentException when argument invalid (null)
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new NullableFieldException("coordinates");

        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    @CsvCustomBindByName(required = true, converter = LocalDateTimeConverter.class)
    final LocalDateTime creationDate = LocalDateTime.now();

    /**
     * get creation date of the band
     * @return creation date of the band
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     */
    @CsvBindByName(required = true)
    private Integer numberOfParticipants;

    /**
     * set number of participants of the band with validation
     * @param numberOfParticipants - number of participants of the band
     * @throws InvalidArgumentException when argument invalid (null or less than 0)
     */
    public void setNumberOfParticipants(Integer numberOfParticipants) {
        if (numberOfParticipants == null) throw new NullableFieldException("numberOfParticipant");
        if (numberOfParticipants <= 0) throw new InvalidArgumentException("numberOfParticipants", "значение поля должно быть больше 0");

        this.numberOfParticipants = numberOfParticipants;
    }

    /**
     * get number of participants of the band
     *
     * @return number of participants of the band
     */
    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    /**
     * Поле может быть null
     * Значение поля должно быть больше 0
     */
    @CsvBindByName
    private Long singlesCount;

    /**
     * set singles count of the band with validation
     * @param singlesCount - singles count of the band
     * @throws InvalidArgumentException when argument invalid (less than 0)
     */
    public void setSinglesCount(Long singlesCount) {
        if (singlesCount <= 0) throw new InvalidArgumentException("singlesCount", "значение поля должно быть больше 0");

        this.singlesCount = singlesCount;
    }

    /**
     * get singles count of the band
     * @return singles count of the band
     */
    public Long getSinglesCount() {
        return singlesCount;
    }

    /**
     * Поле не может быть null
     */
    @CsvBindByName(required = true)
    private MusicGenre genre;

    /**
     * set genre of the band with validation
     * @param genre - genre of the band
     * @throws InvalidArgumentException when argument invalid (null)
     */
    public void setGenre(MusicGenre genre) {
        if (genre == null) throw new NullableFieldException("genre");

        this.genre = genre;
    }

    /**
     * get music genre of the band
     * @return music genre of the band
     */
    public MusicGenre getGenre() {
        return genre;
    }

    /**
     * Поле не может быть null
     */
    @CsvCustomBindByName(required = true, converter = AlbumConverter.class)
    private Album bestAlbum;

    /**
     * set the best album of the band with validation
     * @param bestAlbum - best album of the band
     * @throws InvalidArgumentException when argument invalid (null)
     */
    public void setBestAlbum(Album bestAlbum) {
        if (bestAlbum == null) throw new NullableFieldException("bestAlbum");

        this.bestAlbum = bestAlbum;
    }

    /**
     * get the best album of the band
     * @return the best album of the band
     */
    public Album getBestAlbum() {
        return bestAlbum;
    }

    /**
     * compare this object with another object by number of participants and singles count
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(MusicBand other) {
        return this.numberOfParticipants.compareTo(other.numberOfParticipants) + this.singlesCount.compareTo(other.singlesCount);
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", singlesCount=" + singlesCount +
                ", genre=" + genre +
                ", bestAlbum=" + bestAlbum +
                '}';
    }
}
