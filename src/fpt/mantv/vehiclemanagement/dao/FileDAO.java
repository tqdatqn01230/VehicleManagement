package fpt.mantv.vehiclemanagement.dao;

import java.io.IOException;
import java.util.List;

public interface FileDAO<T> {
    List<T> read() throws IOException;
    void write(List<T> entities) throws IOException;
}
