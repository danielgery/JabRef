package org.jabref.logic.exporter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import org.jabref.model.database.BibDatabaseContext;
import org.jabref.model.entry.BibEntry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertEquals;

public class MsBibExportFormatTest {

    public BibDatabaseContext databaseContext;
    public Charset charset;
    public File tempFile;
    public MSBibExportFormat msBibExportFormat;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();


    @Before
    public void setUp() throws Exception {
        databaseContext = new BibDatabaseContext();
        charset = StandardCharsets.UTF_8;
        msBibExportFormat = new MSBibExportFormat();
        tempFile = testFolder.newFile();
    }

    @Test
    public final void testPerformExportWithNoEntry() throws IOException, SaveException {
        List<BibEntry> entries = Collections.emptyList();
        String tempFileName = tempFile.getCanonicalPath();
        msBibExportFormat.performExport(databaseContext, tempFileName, charset, entries);
        assertEquals(Collections.emptyList(), Files.readAllLines(tempFile.toPath()));
    }
}
