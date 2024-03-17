FROM solr:8.11.2

COPY ./restaurants /opt/solr-init
RUN mv /opt/solr-init/* /var/solr/data/