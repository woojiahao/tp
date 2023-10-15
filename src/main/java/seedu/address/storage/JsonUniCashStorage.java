package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyUniCash;

/**
 * A class to access UniCash data stored as a json file on the hard disk.
 */
public class JsonUniCashStorage implements UniCashStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUniCashStorage.class);

    private final Path filePath;

    public JsonUniCashStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUniCashFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUniCash> readUniCash() throws DataLoadingException {
        return readUniCash(filePath);
    }

    /**
     * Similar to {@link #readUniCash()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyUniCash> readUniCash(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        var jsonUniCash = JsonUtil.readJsonFile(filePath, JsonSerializableUniCash.class);
        if (jsonUniCash.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUniCash.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveUniCash(ReadOnlyUniCash uniCash) throws IOException {
        saveUniCash(uniCash, filePath);
    }

    /**
     * Similar to {@link #saveUniCash(ReadOnlyUniCash)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUniCash(ReadOnlyUniCash uniCash, Path filePath) throws IOException {
        requireNonNull(uniCash);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUniCash(uniCash), filePath);
    }

}
