# Makefile for generating Javadoc and cleaning

SRC_DIR = fooddatacentral
DOCS_DIR = doc
JAVA_FILES = $(shell find $(SRC_DIR) -name '*.java')



all: $(DOCS_DIR)

$(DOCS_DIR): $(JAVA_FILES)
	@mkdir -p $(DOCS_DIR)
	@javadoc -d $(DOCS_DIR) $(JAVA_FILES)

clean:
	@rm -rf $(DOCS_DIR)

.PHONY: all clean
