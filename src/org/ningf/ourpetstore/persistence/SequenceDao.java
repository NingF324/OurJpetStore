package org.ningf.ourpetstore.persistence;

import org.ningf.ourpetstore.domain.Sequence;

public interface SequenceDao {

    Sequence getSequence(Sequence sequence);

    void updateSequence(Sequence sequence);
}
