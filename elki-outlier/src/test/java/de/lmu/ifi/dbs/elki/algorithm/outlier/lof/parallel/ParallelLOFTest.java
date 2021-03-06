/*
 * This file is part of ELKI:
 * Environment for Developing KDD-Applications Supported by Index-Structures
 *
 * Copyright (C) 2017
 * ELKI Development Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.lmu.ifi.dbs.elki.algorithm.outlier.lof.parallel;

import org.junit.Test;

import de.lmu.ifi.dbs.elki.algorithm.outlier.AbstractOutlierAlgorithmTest;
import de.lmu.ifi.dbs.elki.algorithm.outlier.lof.LOF;
import de.lmu.ifi.dbs.elki.data.DoubleVector;
import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.result.outlier.OutlierResult;
import de.lmu.ifi.dbs.elki.utilities.ClassGenericsUtil;
import de.lmu.ifi.dbs.elki.utilities.optionhandling.parameterization.ListParameterization;

/**
 * Regression tests the ParallelLOF algorithm.
 *
 * @author Erich Schubert
 * @since 0.4.0
 */
public class ParallelLOFTest extends AbstractOutlierAlgorithmTest {
  @Test
  public void testParallelLOF() {
    Database db = makeSimpleDatabase(UNITTEST + "outlier-axis-subspaces-6d.ascii", 1345);

    // Parameterization
    ListParameterization params = new ListParameterization();
    params.addParameter(LOF.Parameterizer.K_ID, 10);

    // setup Algorithm
    ParallelLOF<DoubleVector> lof = ClassGenericsUtil.parameterizeOrAbort(ParallelLOF.class, params);
    testParameterizationOk(params);

    // run ParallelLOF on database
    OutlierResult result = lof.run(db);

    testSingleScore(result, 1293, 1.1945314199156365);
    testAUC(db, "Noise", result, 0.8921680672268908);
  }
}