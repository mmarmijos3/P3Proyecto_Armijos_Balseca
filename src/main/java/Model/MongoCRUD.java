package Model;

import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoCRUD {

    private final MongoConnection mongo = MongoConnection.getInstance();

    public boolean create(Document document) {
        try {
            mongo.getCollection().insertOne(document);
            return true;
        } catch (MongoException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    public List<Document> readAll() {
        List<Document> list = new ArrayList<>();
        try {
            mongo.getCollection().find().forEach(list::add);
        } catch (MongoException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
        return list;
    }

    public boolean deleteByImo(String imo) {
        try {
            mongo.getCollection().deleteOne(Filters.eq("imo", imo));
            return true;
        } catch (MongoException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public boolean updateByImo(String imo, Document updates) {
        try {
            mongo.getCollection().updateOne(
                Filters.eq("imo", imo),
                new Document("$set", updates)
            );
            return true;
        } catch (MongoException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public Document findByImo(String imo) {
        try {
            return mongo.getCollection().find(Filters.eq("imo", imo)).first();
        } catch (MongoException e) {
            System.err.println("Error al buscar: " + e.getMessage());
            return null;
        }
    }

    public boolean clearQueue() {
        try {
            mongo.getCollection().deleteMany(new Document());
            return true;
        } catch (MongoException e) {
            System.err.println("Error al limpiar cola: " + e.getMessage());
            return false;
        }
    }
}