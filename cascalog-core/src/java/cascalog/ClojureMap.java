/*
    Copyright 2010 Nathan Marz
 
    Project and contact information: http://www.cascalog.org/ 

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
   
        http://www.apache.org/licenses/LICENSE-2.0
   
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package cascalog;

import cascading.flow.FlowProcess;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import clojure.lang.IFn;
import clojure.lang.ISeq;

public class ClojureMap extends ClojureCascadingBase implements Function {
  public ClojureMap(Fields outputFields, IFn fn) {
    super(outputFields, fn);
  }

  public void operate(FlowProcess fp, FunctionCall call) {
    ISeq fnArgs = Util.coerceFromTuple(call.getArguments().getTuple());
    Object res = applyFunction(fnArgs);
    call.getOutputCollector().add(Util.coerceToTuple(res));
  }
}
