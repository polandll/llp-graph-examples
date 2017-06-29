/*
Simple CSV data loader which creates vertices of a configured label which have their properties set to the fields
in the CSV file using the CSV field headers as property keys.
 */

config create_schema: true //This loading script will initialize the schema if it does not already exists

def input = File.csv(filename).compression(compress)
def labelName = label.isEmpty()?org.apache.commons.io.FilenameUtils.getBaseName(input.getFileName()):label
def keyName = key

load(input).asVertices {
    label labelName
    key keyName
}