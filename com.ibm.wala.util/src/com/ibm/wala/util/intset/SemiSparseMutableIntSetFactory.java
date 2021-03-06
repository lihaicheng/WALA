/*******************************************************************************
 * Copyright (c) 2002 - 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.wala.util.intset;

import java.util.Iterator;
import java.util.TreeSet;

/**
 */
public class SemiSparseMutableIntSetFactory implements MutableIntSetFactory<SemiSparseMutableIntSet> {

  /**
   * @param set
   * @throws IllegalArgumentException  if set is null
   */
  @Override
  public SemiSparseMutableIntSet make(int[] set) {
    if (set == null) {
      throw new IllegalArgumentException("set is null");
    }
    if (set.length == 0) {
      return make();
    } else {
      // XXX not very efficient.
      TreeSet<Integer> T = new TreeSet<>();
      for (int i = 0; i < set.length; i++) {
        T.add(Integer.valueOf(set[i]));
      }
      SemiSparseMutableIntSet result = new SemiSparseMutableIntSet();
      for (Iterator<Integer> it = T.iterator(); it.hasNext();) {
        Integer I = it.next();
        result.add(I.intValue());
      }
      return result;
    }
  }

  /**
   * @param string
   */
  @Override
  public SemiSparseMutableIntSet parse(String string) throws NumberFormatException {
    int[] data = SparseIntSet.parseIntArray(string);
    SemiSparseMutableIntSet result = new SemiSparseMutableIntSet();
    for (int i = 0; i < data.length; i++)
      result.add(data[i]);
    return result;
  }

  /*
   * @see com.ibm.wala.util.intset.MutableIntSetFactory#make(com.ibm.wala.util.intset.IntSet)
   */
  @Override
  public SemiSparseMutableIntSet makeCopy(IntSet x) {
    SemiSparseMutableIntSet y = new SemiSparseMutableIntSet();
    y.copySet(x);
    return y;
  }

  /*
   * @see com.ibm.wala.util.intset.MutableIntSetFactory#make()
   */
  @Override
  public SemiSparseMutableIntSet make() {
    return new SemiSparseMutableIntSet();
  }

}
